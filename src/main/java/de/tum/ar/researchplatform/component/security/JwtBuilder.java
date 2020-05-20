package de.tum.ar.researchplatform.component.security;

import de.tum.ar.researchplatform.util.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

import static de.tum.ar.researchplatform.util.Constants.*;

/**
 * Created by karthik on 5/16/2020
 */
@Component
public class JwtBuilder {

    /***
     * Build a JWT for the given user Id with a user role
     * @param userId userId
     * @param loginId loginId
     * @param sessionId sessionId
     * @return token
     */
    public String buildJwtForUser(String userId, String loginId, String sessionId) {
        return this.buildJwtWithRole(userId, loginId, sessionId, Constants.ROLE_USER);
    }

    /***
     * Build a JWT for the given user Id with an admin role
     * @param userId userId
     * @param loginId loginId
     * @param sessionId sessionId
     * @return token
     */
    public String buildJwtForAdmin(String userId, String loginId, String sessionId) {
        return this.buildJwtWithRole(userId, loginId, sessionId, Constants.ROLE_ADMIN);
    }

    private String buildJwtWithRole(String userId, String loginId, String sessionId, String role) {
        byte[] signingKey = JWT_SECRET.getBytes();
        return Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam(TOKEN_CLAIM_TYPE, TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(userId)
                .claim(TOKEN_CLAIM_ROLE, role)
                .claim(TOKEN_CLAIM_SESSIONID, sessionId)
                .claim(TOKEN_CLAIM_LOGINID, loginId)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY_MS)).compact();
    }
}
