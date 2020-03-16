package pl.edu.pja.prz.core.security;

import static pl.edu.pja.prz.core.configuration.SecurityConstants.AUTH_LOGIN_URL;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class JwtAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	public JwtAuthenticationEntryPoint()  {
		super(AUTH_LOGIN_URL);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}
}
