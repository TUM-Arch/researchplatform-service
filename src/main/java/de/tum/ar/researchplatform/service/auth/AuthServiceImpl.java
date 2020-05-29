package de.tum.ar.researchplatform.service.auth;

import com.google.common.collect.Lists;
import de.tum.ar.researchplatform.util.Admins;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static de.tum.ar.researchplatform.util.Constants.*;

/**
 * Created by karthik on 5/16/2020
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private List<String> adminList = Lists.newArrayList(Arrays.asList(Admins.admins));

    @Override
    public HashMap<String, String> attemptLogin(String userId, String password) {
        HashMap<String, String> cookies = new HashMap<String, String>();
        if(adminList.contains(userId)) {
            cookies.put(ADMIN_HEADER, Boolean.toString(true));
        }
        else {
            cookies.put(ADMIN_HEADER, Boolean.toString(false));
        }
        String sessionId = null;
        String loginId = null;
        String locationUrl = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        MultiValueMap<String, String> formVars = new LinkedMultiValueMap<String, String>();

        // Initial request to fetch login id
        formVars.add(TUM_ONLINE_LOGIN_USERID_PARAM, userId);
        formVars.add(TUM_ONLINE_LOGIN_PASSWORD_PARAM, password);
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formVars, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(TUM_ONLINE_BASE_URL+TUM_ONLINE_LOGIN_URL, HttpMethod.POST, request, String.class);
        if(!response.getStatusCode().equals(HttpStatus.FOUND))
            return cookies;
        // Response received
        HttpHeaders responseHeaders = response.getHeaders();
        loginId = extractAndReturnLoginId(responseHeaders);
        locationUrl = extractAndReturnLocation(responseHeaders);
        if(loginId == null || locationUrl == null)
            return cookies;

        // Subsequent request to carry out login
        requestHeaders.add(COOKIE_HEADER, extractAndReturnLoginIdBaseValue(responseHeaders));
        request = new HttpEntity<>(formVars, requestHeaders);
        response = restTemplate.exchange(TUM_ONLINE_BASE_URL + locationUrl, HttpMethod.POST, request, String.class);
        if(!response.getStatusCode().equals(HttpStatus.FOUND))
            return cookies;
        // Response received
        String responseBody = response.getBody().toString();
        if(responseBody.contains(INVALID_LOGIN_MESSAGE_GERMAN) || responseBody.contains(INVALID_LOGIN_MESSAGE_ENGLISH))
            return cookies;
        responseHeaders = response.getHeaders();
        sessionId = extractAndReturnSessionId(responseHeaders);
        if(sessionId == null)
            return cookies;

        cookies.put(COOKIE_STRING_LOGIN, loginId);
        cookies.put(COOKIE_STRING_SESSION, sessionId);
        return cookies;
    }

    @Override
    public void attemptLogout(String userId, String loginId, String sessionId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(COOKIE_HEADER, SESSION_REQUEST_HEADER+sessionId);
        HttpEntity<List<String>> request = new HttpEntity<>(requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(TUM_ONLINE_BASE_URL + TUM_ONLINE_LOGOUT_URL, HttpMethod.GET, request, String.class);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            log.error("Unable to logout user {}", userId);
    }

    private String extractAndReturnLoginId(HttpHeaders responseHeaders) {
        String setLoginCookieString = responseHeaders.getFirst(SET_COOKIE_HEADER);
        List<String> loginCookieBaseValues = Arrays.asList(setLoginCookieString.split(DELIMETER_SEMI_COLON));
        if(loginCookieBaseValues.size() > 0) {
            List<String> loginCookieActualValues = Arrays.asList(loginCookieBaseValues.get(0).split(DELIMETER_EQUAL));
            if(loginCookieActualValues.size() > 1)
                return loginCookieActualValues.get(1);
        }
        return null;
    }

    private String extractAndReturnLoginIdBaseValue(HttpHeaders responseHeaders) {
        String setLoginCookieString = responseHeaders.getFirst(SET_COOKIE_HEADER);
        List<String> loginCookieBaseValues = Arrays.asList(setLoginCookieString.split(DELIMETER_SEMI_COLON));
        if(loginCookieBaseValues.size() > 0) {
            return loginCookieBaseValues.get(0);
        }
        return null;
    }

    private String extractAndReturnSessionId(HttpHeaders responseHeaders) {
        String setSessionCookieString = responseHeaders.getFirst(SET_COOKIE_HEADER);
        List<String> sessionCookieBaseValues = Arrays.asList(setSessionCookieString.split(DELIMETER_SEMI_COLON));
        if(sessionCookieBaseValues.size() > 0) {
            List<String> sessionCookieActualValues = Arrays.asList(sessionCookieBaseValues.get(0).split(DELIMETER_EQUAL));
            if(sessionCookieActualValues.size() > 1)
                return sessionCookieActualValues.get(1);
        }
        return null;
    }

    private String extractAndReturnLocation(HttpHeaders responseHeaders) {
        String location;
        if(!(location = responseHeaders.getFirst(LOCATION_HEADER)).isEmpty())
            return location;
        return null;
    }
}
