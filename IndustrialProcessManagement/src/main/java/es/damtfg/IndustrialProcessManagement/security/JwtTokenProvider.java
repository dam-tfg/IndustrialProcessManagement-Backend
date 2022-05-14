/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import es.damtfg.IndustrialProcessManagement.repository.user.security.UserPrincipal;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Alberto González
 *
 */
@Component
public class JwtTokenProvider {

	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expirationInMs}")
	private int jwtExpirationInMs;
	
	public String generateToken(Authentication authentication) {
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
		
		return Jwts.builder()
				.setSubject(Long.toString(userPrincipal.getId()))
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();		
	}
	
	public Long getUserIdFromJwt(String token) {
		
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean validateToken(String authToken) {
		
		try {
			
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			
			return true;
			
		} catch (SignatureException ex) {
			
            LOG.error(AppMessages.LOG_ERROR_JWT_INVALID_SIGNATURE);
            
        } catch (MalformedJwtException ex) {
        	
        	LOG.error(AppMessages.LOG_ERROR_JWT_INVALID_TOKEN);
            
        } catch (ExpiredJwtException ex) {
        	
        	LOG.error(AppMessages.LOG_ERROR_JWT_EXPIRED_TOKEN);
            
        } catch (UnsupportedJwtException ex) {
        	
        	LOG.error(AppMessages.LOG_ERROR_JWT_UNSUPPORTED_TOKEN);
            
        } catch (IllegalArgumentException ex) {
        	
        	LOG.error(AppMessages.LOG_ERROR_JWT_CLAIMS_EMPTY);
        }
		
		return false;		
	}
	
}
