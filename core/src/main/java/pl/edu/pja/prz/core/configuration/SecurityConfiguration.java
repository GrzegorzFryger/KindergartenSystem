package pl.edu.pja.prz.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.edu.pja.prz.core.security.JwtAuthenticationEntryPoint;
import pl.edu.pja.prz.core.security.JwtAuthenticationFilter;
import pl.edu.pja.prz.core.security.JwtAuthorizationFilter;
import pl.edu.pja.prz.core.security.JwtTokenProvider;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static pl.edu.pja.prz.core.configuration.SecurityConstants.AUTH_LOGIN_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private AuthenticationProvider authenticationProvider;
    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public void setJwtAuthenticationEntryPoint(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(super.authenticationManagerBean(), jwtTokenProvider))
                .addFilter(new JwtAuthorizationFilter(super.authenticationManagerBean(), jwtTokenProvider))
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }

}
