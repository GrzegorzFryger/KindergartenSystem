package pl.edu.pja.prz.account.domain;

public interface PasswordMenager {
	String encode(CharSequence rawPassword);
	boolean matches(CharSequence rawPassword, String encodePassword);
}
