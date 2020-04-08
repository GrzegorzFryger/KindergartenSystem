package pl.edu.pja.prz.commons.constants;

/**
 * This class contains all constants, which are required for authorization purposes.<br><br>
 *
 * All below constants are 1:1 mappings with PrivilegeType enum from account module<br><br>
 *
 * Example mapping:<br>
 * {@link Roles#USER} - {@link pl.edu.pja.prz.account.model.enums.PrivilegeType#USER}<br><br>
 *
 * Roles hierarchy:<br>
 * ADMINISTRATOR -> [Administrator, Teacher, User]<br>
 * TEACHER -> [Teacher, User]<br>
 * USER -> [User]<br>
 */
public class Roles {
    private static final String ROLE_USER = "hasAuthority('USER')";
    private static final String ROLE_TEACHER = "hasAuthority('TEACHER')";
    private static final String ROLE_ADMINISTRATOR = "hasAuthority('ADMINISTRATOR')";

    private static final String OR = " or ";

    /**
     * Endpoints with this role in @PreAuthorize annotation can be accessed by:
     * <ul>
     *     <li>USER</li>
     *     <li>TEACHER</li>
     *     <li>ADMINISTRATOR</li>
     * </ul>
     */
    public static final String HAS_ROLE_USER = ROLE_USER + OR + ROLE_TEACHER + OR + ROLE_ADMINISTRATOR;

    /**
     * Endpoints with this role in @PreAuthorize annotation can be accessed by:
     * <ul>
     *     <li>TEACHER</li>
     *     <li>ADMINISTRATOR</li>
     * </ul>
     */
    public static final String HAS_ROLE_TEACHER = ROLE_TEACHER + OR + ROLE_ADMINISTRATOR;

    /**
     * Endpoints with this role in @PreAuthorize annotation can be accessed by:
     * <ul>
     *     <li>ADMINISTRATOR</li>
     * </ul>
     */
    public static final String HAS_ROLE_ADMIN = ROLE_ADMINISTRATOR;

    private Roles() {

    }
}
