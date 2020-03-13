package pl.edu.pja.prz.core.security;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.edu.pja.prz.core.model.AuthDto;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.POST;
import static pl.edu.pja.prz.core.configuration.SecurityConstants.AUTH_LOGIN_URL;
import static pl.edu.pja.prz.core.configuration.SecurityConstants.TOKEN;
import static pl.edu.pja.prz.core.utilites.JwtFilterUtils.addErrorToResponse;
import static pl.edu.pja.prz.core.utilites.JwtFilterUtils.getAuthDto;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl(AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (!POST.name().equals(request.getMethod())) {
            return respondWithError(request.getMethod() + " method is not supported! Make sure to use " + POST.name(), response);
        }

        AuthDto dto = getAuthDto(request);
        if (dto == null) {
            return respondWithError("Authentication failure", response);
        }

        var username = dto.getUsername();
        var password = dto.getPassword();

        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authenticationToken);
        return super.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) throws IOException {
        var user = ((JwtUserDetails) authentication.getPrincipal());

        var roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = buildToken(user, roles);
        response.getWriter().write(token);
    }

    private String buildToken(JwtUserDetails user, List<String> roles) {
        String tokenString = jwtTokenProvider.buildToken(user, roles);
        String token = "";
        try {
            token = new JSONObject()
                    .put(TOKEN, tokenString)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    private Authentication respondWithError(String message, HttpServletResponse response) {
        addErrorToResponse(message, response);
        return null;
    }
}
