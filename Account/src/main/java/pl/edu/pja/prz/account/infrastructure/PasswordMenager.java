package pl.edu.pja.prz.account.infrastructure;

public interface PasswordMenager {
	String encode(CharSequence rawPassword);
	boolean matches(CharSequence rawPassword, String encodePassword);
}
