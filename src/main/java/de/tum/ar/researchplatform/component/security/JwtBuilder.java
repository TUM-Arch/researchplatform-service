package de.tum.ar.researchplatform.component.security;

import de.tum.ar.researchplatform.util.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static de.tum.ar.researchplatform.util.Constants.*;

/**
 * Created by karthik on 5/16/2020
 */
@Component
public class JwtBuilder {

    /***
     * Build a JWT for the given user Id with a user role
     * @param userId userId
     * @return token
     */
    public String buildJwtForUser(String userId, String sessionId) {
        List<String> roles = new ArrayList<>();
        roles.add(Constants.ROLE_USER);
        return this.buildJwtWithRole(userId, sessionId, roles);
    }

    /***
     * Build a JWT for the given user Id with an admin role
     * @param userId userId
     * @return token
     */
    public String buildJwtForAdmin(String userId,  String sessionId) {
        List<String> roles = new ArrayList<>();
        roles.add(Constants.ROLE_ADMIN);
        return this.buildJwtWithRole(userId, sessionId, roles);
    }

    private String buildJwtWithRole(String userId, String sessionId, List<String> roles) {
        byte[] signingKey = JWT_SECRET.getBytes();
        return Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(userId)
                .claim("role", roles)
                .claim("sessionId", sessionId)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY_MS)).compact();
    }
}
