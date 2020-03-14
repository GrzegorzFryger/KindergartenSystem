package pl.edu.pja.prz.commons.constants;

/**
 * This class contains all constants, which are required for authorization purposes.<br><br>
 *
 * All below constants are 1:1 mappings with PrivilegeType enum from account module<br><br>
 *
 * Example mapping:<br>
 * {@link Roles#USER} - {@link pl.edu.pja.prz.account.model.enums.PrivilegeType#USER}
 */
public class Roles {
    public static final String OR = " or ";
    public static final String AND = " and ";

    public static final String USER = "USER";
    public static final String HAS_ROLE_USER = "hasAuthority('" + USER + "')";
    public static final String HAS_NOT_ROLE_USER = "!hasAuthority('" + USER + "')";

    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String HAS_ROLE_ADMIN = "hasAuthority('" + ADMINISTRATOR + "')";
    public static final String HAS_NOT_ROLE_ADMIN = "!hasAuthority('" + ADMINISTRATOR + "')";

    public static final String TEACHER = "TEACHER";
    public static final String HAS_ROLE_TEACHER = "hasAuthority('" + TEACHER + "')";
    public static final String HAS_NOT_ROLE_TEACHER = "!hasAuthority('" + TEACHER + "')";

    public static final String HAS_ANY_ROLE = HAS_ROLE_USER + OR + HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN;

    private Roles() {

    }
}
