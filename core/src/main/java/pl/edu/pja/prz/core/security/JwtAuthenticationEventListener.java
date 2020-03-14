package pl.edu.pja.prz.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEventListener implements ApplicationListener<ApplicationEvent> {
	private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof AbstractAuthenticationFailureEvent) {
			String username = ((AbstractAuthenticationFailureEvent) applicationEvent).getAuthentication().getName();
			if (applicationEvent instanceof AuthenticationFailureBadCredentialsEvent) {
				logger.info(String.format("AbstractAuthenticationFailureEvent: for user %s", username));
			}
		}

		if (applicationEvent instanceof AuthenticationFailureDisabledEvent) {
			String username = ((AbstractAuthenticationFailureEvent) applicationEvent).getAuthentication().getName();
			logger.info(String.format("AuthenticationFailureDisabledEvent: for user %s", username));
		}
	}
}
