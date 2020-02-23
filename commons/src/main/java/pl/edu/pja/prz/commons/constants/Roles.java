package pl.edu.pja.prz.commons.constants;

/**
 * This class contains all constants, which are required for authorization purposes.<br><br>
 */
public class Roles {
    public static final String USER = "USER";
    public static final String HAS_ROLE_USER = "hasRole('" + USER + "')";
    public static final String HAS_NOT_ROLE_USER = "!hasRole('" + USER + "')";

    public static final String ADMIN = "ADMIN";
    public static final String HAS_ROLE_ADMIN = "hasRole('" + ADMIN + "')";
    public static final String HAS_NOT_ROLE_ADMIN = "!hasRole('" + ADMIN + "')";

    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String HAS_ROLE_EMPLOYEE = "hasRole('" + EMPLOYEE + "')";
    public static final String HAS_NOT_ROLE_EMPLOYEE = "!hasRole('" + EMPLOYEE + "')";

    private Roles() {

    }
}
