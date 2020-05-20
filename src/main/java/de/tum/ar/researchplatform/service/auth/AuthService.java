package de.tum.ar.researchplatform.service.auth;

import java.util.HashMap;

/**
 * Created by karthik on 5/16/2020
 */
public interface AuthService {
    /**
     * Forward login request and attempt login
     * @param userId
     * @param password
     * @return response cookies
     */
    HashMap<String, String> attemptLogin(String userId, String password);

    /**
     * Forward logout request and attempt logout
     * @param userId
     * @param sessionId
     * @param loginId
     */
    void attemptLogout(String userId, String loginId, String sessionId);
}
