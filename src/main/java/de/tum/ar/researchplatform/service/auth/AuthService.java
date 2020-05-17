package de.tum.ar.researchplatform.service.auth;

import de.tum.ar.researchplatform.exception.CustomLoginException;
import org.springframework.util.MultiValueMap;

/**
 * Created by karthik on 5/16/2020
 */
public interface AuthService {

    /**
     * Temporary
     * @param userId
     * @param password
     * @return true if user, false if admin
     */
    boolean attemptTempLogin(String userId, String password) throws CustomLoginException;

    /**
     * Forward login request and attempt login
     * @param userId
     * @param password
     * @return response cookies
     */
    MultiValueMap<String, String> attemptLogin(String userId, String password);

    /**
     * Forward logout request and attempt logout
     * @param userId
     * @param sessionId
     * @return response cookies
     */
    MultiValueMap<String, String> attemptLogout(String userId, String sessionId);
}
