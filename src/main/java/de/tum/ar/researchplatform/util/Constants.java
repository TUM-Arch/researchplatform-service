package de.tum.ar.researchplatform.util;

/**
 * Created by karthik on 9/10/2019
 */

/**
 * Helper class to provide constants
 */
public final class Constants {

    /**
     * Enforce private constructor
     */
    private Constants() {}

    /**
     * Enums
     */

    //Enum for Project object
    public enum ProjectStatus {
        NOTSUBMITTED,
        SUBMITTED,
        APPROVED,
        REJECTED
    }

    /**
     * Strings
     */
    // AUTH
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_CLAIM_TYPE = "typ";
    public static final String TOKEN_CLAIM_ROLE = "role";
    public static final String TOKEN_CLAIM_SESSIONID = "sessionId";
    public static final String TOKEN_CLAIM_LOGINID = "loginId";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String ADMIN_HEADER = "Admin";
    public static final String SET_COOKIE_HEADER = "Set-Cookie";
    public static final String LOCATION_HEADER = "Location";
    public static final String COOKIE_HEADER = "Cookie";
    public static final String LOGIN_REQUEST_HEADER = "PLOGINID=";
    public static final String SESSION_REQUEST_HEADER = "PSESSIONID=";
    public static final String COOKIE_STRING_LOGIN = "LOGINID";
    public static final String COOKIE_STRING_SESSION = "SESSIONID";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String INVALID_LOGIN_MESSAGE_GERMAN = "Benutzername oder Kennwort ungültig";
    public static final String INVALID_LOGIN_MESSAGE_ENGLISH = "User name or password invalid";

    // MESSAGES
    public static final String FIELD_NOT_FOUND_MSG = "Field not found";
    public static final String IMAGE_NOT_FOUND_MSG = "Image not found";
    public static final String PROJECT_NOT_FOUND_MSG = "Project not found";
    public static final String USER_NOT_FOUND_MSG = "User not found";
    public static final String LOGIN_FAILED_MSG = "Login failed";

    // TUM_ONLINE
    public static final String TUM_ONLINE_BASE_URL = "https://campus.tum.de/tumonline/";
    public static final String TUM_ONLINE_LOGIN_URL = "wbAnmeldung.durchfuehren";
    public static final String TUM_ONLINE_LOGOUT_URL = "anmeldung.beenden";
    public static final String TUM_ONLINE_LOGIN_USERID_PARAM = "cp1";
    public static final String TUM_ONLINE_LOGIN_PASSWORD_PARAM = "cp2";

    // JWT
    public static final String JWT_SECRET = "G!@d`yyQZCZzS^bpVh7s<{coE[n&BA>kJ~jTZ*1OyfQ?De~~AK/1W.NM|l7U2M3d";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "TUMArch";
    public static final String TOKEN_AUDIENCE = "TUMArchApp";
    public static final Integer TOKEN_EXPIRY_MS = 21600000;
    public static final Integer TOKEN_EXPIRY_S = 21600;

    // OTHER
    public static final String DELIMETER_SEMI_COLON = ";";
    public static final String DELIMETER_EQUAL = "=";
    public static final String USERID_HEADER = "userId";
}
