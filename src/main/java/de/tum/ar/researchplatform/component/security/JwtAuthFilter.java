package de.tum.ar.researchplatform.component.security;

import com.google.common.collect.Lists;
import de.tum.ar.researchplatform.exception.CustomLoginException;
import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.service.auth.AuthServiceImpl;
import de.tum.ar.researchplatform.service.user.UserServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static de.tum.ar.researchplatform.util.Constants.*;

/**
 * Created by karthik on 5/16/2020
 */
@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    ServletContext servletContext;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthServiceImpl loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(servletContext == null) {
            servletContext = request.getServletContext();
        }
        if (userService == null) {
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = Objects.requireNonNull(webApplicationContext).getBean(UserServiceImpl.class);
        }
        if (loginService == null) {
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            loginService = Objects.requireNonNull(webApplicationContext).getBean(AuthServiceImpl.class);
        }
        String header = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(header) || !header.startsWith(TOKEN_PREFIX)) {
            String requestURI = request.getRequestURI();
            String requestMethod = request.getMethod();
            if(requestMethod.equals("POST") && (requestURI.equals("/api/login/") || requestURI.equals("/api/login"))) {
                JwtBuilder jwtBuilder = new JwtBuilder();
                String userId = request.getHeader("userId");
                String password = request.getHeader("password");
                String sessionId = null;
                String loginId = null;
                String jwt = null;
                User user = null;
                Boolean loggedInAsAdmin = null;
                try {
                     HashMap<String, String> cookies = loginService.attemptLogin(userId, password);
                     if(!cookies.containsKey(COOKIE_STRING_SESSION))
                         throw new CustomLoginException(LOGIN_FAILED_MSG);
                    loggedInAsAdmin = Boolean.parseBoolean(cookies.get(ADMIN_HEADER));
                    loginId = cookies.get(COOKIE_STRING_LOGIN);
                    sessionId = cookies.get(COOKIE_STRING_SESSION);
                    if(loggedInAsAdmin) {
                        jwt = jwtBuilder.buildJwtForAdmin(userId, loginId, sessionId);
                    }
                    else {
                        jwt = jwtBuilder.buildJwtForUser(userId, loginId, sessionId);
                    }
                    userService.findByTumId(userId);
                } catch (CustomLoginException e) {
                    throw new AccessDeniedException(LOGIN_FAILED_MSG);
                } catch (CustomNotFoundException e) {
                    user = new User(userId);
                    userService.saveOrUpdate(user);
                }
                if(!response.containsHeader(TOKEN_HEADER))
                    response.addHeader(TOKEN_HEADER, "Bearer " + jwt);
                if(!response.containsHeader(ADMIN_HEADER))
                    response.addHeader(ADMIN_HEADER, String.valueOf(loggedInAsAdmin));
                if(!response.containsHeader(ACCESS_CONTROL_EXPOSE_HEADERS))
                    response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, new String(TOKEN_HEADER + "," + ADMIN_HEADER));
            }
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(header, request, response));
        filterChain.doFilter(request, response);
    }

    /**
     * Gets an anon authentication for given token
     * @param token Token
     * @return AnonymousAuthenticationToken
     */
    public AnonymousAuthenticationToken getAuthentication(String token, HttpServletRequest request, HttpServletResponse response) {
        AnonymousAuthenticationToken anonymousAuthenticationToken = null;
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        try {
            byte[] signingKey = JWT_SECRET.getBytes();
            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.replace("Bearer ", "")).getBody();
            String userId = claims.getSubject();

            String role = (String) claims.get(TOKEN_CLAIM_ROLE);
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            List<SimpleGrantedAuthority> authorities = Lists.newArrayList(authority);

            String sessionId = (String) claims.get(TOKEN_CLAIM_SESSIONID);
            String loginId = (String) claims.get(TOKEN_CLAIM_LOGINID);

            User user = userService.findByTumId(userId);
            String header = request.getHeader(USERID_HEADER);
            if (user != null && !StringUtils.isEmpty(sessionId)) {
                anonymousAuthenticationToken = new AnonymousAuthenticationToken(userId, user, authorities);
                if(!StringUtils.isEmpty(header) && (role.contains(ROLE_USER) || role.contains(ROLE_ADMIN)) && requestMethod.equals("GET") && (requestURI.equals("/api/logout/") || requestURI.equals("/api/logout"))) {
                    loginService.attemptLogout(userId, loginId, sessionId);
                }
            }
        } catch (ExpiredJwtException exception) {
            log.error("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.error("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
        } catch (SignatureException exception) {
            log.error("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
        } catch (CustomNotFoundException e) {
            log.error(USER_NOT_FOUND_MSG);
        }
        return anonymousAuthenticationToken;
    }
}
