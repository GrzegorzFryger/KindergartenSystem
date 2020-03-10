package pl.edu.pja.prz.account.utilites;

public interface PasswordManager {
	String encode(CharSequence rawPassword);
	boolean matches(CharSequence rawPassword, String encodePassword);
	String generateEncodeRandomPassword();
}
