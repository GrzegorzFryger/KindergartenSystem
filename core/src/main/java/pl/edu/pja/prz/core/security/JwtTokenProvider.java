package pl.edu.pja.prz.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static pl.edu.pja.prz.core.configuration.SecurityConstants.*;

@Component
public class JwtTokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	private static final String HEADER_PARAM_NAME = "type";
	private static final String CLAIM_PARAM_NAME = "roles";

	public JwtTokenProvider() {
	}

	public String buildToken(JwtUserDetails user, List<String> roles) {
		var signingKey = JWT_SECRET.getBytes();

		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(signingKey), HS512)
				.setHeaderParam(HEADER_PARAM_NAME, TOKEN_TYPE)
				.setIssuer(TOKEN_ISSUER)
				.setAudience(TOKEN_AUDIENCE)
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.claim(CLAIM_PARAM_NAME, roles)
				.compact();
	}

	public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		var token = request.getHeader(TOKEN_HEADER);

		if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {

			try {
				var parsedToken = parseToken(token);
				var username = parseUsername(parsedToken);
				var authorities = parseAuthorities(parsedToken);

				if (StringUtils.isNotEmpty(username)) {
					return new UsernamePasswordAuthenticationToken(username, null, authorities);
				}
			} catch (ExpiredJwtException exception) {
				logger.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
			} catch (UnsupportedJwtException exception) {
				logger.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
			} catch (MalformedJwtException exception) {
				logger.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
			} catch (SignatureException exception) {
				logger.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
			} catch (IllegalArgumentException exception) {
				logger.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
			}
		}

		return null;
	}

	private Jws<Claims> parseToken(String token) {
		var signingKey = JWT_SECRET.getBytes();

		return Jwts.parserBuilder()
				.setSigningKey(signingKey)
				.build()
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
	}

	private String parseUsername(Jws<Claims> parsedToken) {
		return parsedToken.getBody().getSubject();
	}

	private List<SimpleGrantedAuthority> parseAuthorities(Jws<Claims> parsedToken) {
		return ((List<?>) parsedToken.getBody()
				.get(CLAIM_PARAM_NAME)).stream()
				.map(authority -> new SimpleGrantedAuthority((String) authority))
				.collect(Collectors.toList());
	}
}
