package pl.edu.pja.prz.account.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.ActivateTokenData;

import java.util.Date;
import java.util.Optional;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
public class ActivateTokenService {
	private static final Logger logger = LoggerFactory.getLogger(ActivateTokenService.class);

	//todo refactor/ move constant into other class
	public static final String JWT_SECRET = "gUkXp2s5v8y/B?E(G+KbPeShVmYq3t6w9z$C&F)J@McQfTjWnZr4u7x!A%D*G-Ka";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";
	public static final Integer TOKEN_EXPIRATION_TIME = 300_000; // 5 minutes

	private static final String HEADER_PARAM_NAME = "type";
	private static final String CLAIM_PARAM_NAME = "password-token";

	public ActivateTokenService() {
	}

	public String generateToken(String email, String hashPassword) {
		var signingKey = JWT_SECRET.getBytes();

		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(signingKey), HS512)
				.setHeaderParam(HEADER_PARAM_NAME, TOKEN_TYPE)
				.setIssuer(TOKEN_ISSUER)
				.setAudience(TOKEN_AUDIENCE)
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.claim(CLAIM_PARAM_NAME, hashPassword)
				.compact();
	}

	public Optional<ActivateTokenData> getAuthentication(String token) {
		try {
			var parsedToken = parseToken(token);
			var username = parseUsername(parsedToken);
			var hashPassword = parseAuthorities(parsedToken);

			if (StringUtils.isNotEmpty(username)) {
				return Optional.of(new ActivateTokenData(username, hashPassword));
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
		return Optional.empty();
	}

	private Jws<Claims> parseToken(String token) {
		var signingKey = JWT_SECRET.getBytes();

		return Jwts.parserBuilder()
				.setSigningKey(signingKey)
				.build()
				.parseClaimsJws(token);
	}

	private String parseUsername(Jws<Claims> parsedToken) {
		return parsedToken.getBody().getSubject();
	}

	private String parseAuthorities(Jws<Claims> parsedToken) {
		return (String) parsedToken.getBody().get(CLAIM_PARAM_NAME);
	}
}
