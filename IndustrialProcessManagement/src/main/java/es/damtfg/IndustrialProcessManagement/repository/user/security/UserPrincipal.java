/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.repository.user.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.damtfg.IndustrialProcessManagement.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alberto González
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 2605409388446254622L;
	
	private Long id;
	private String username;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	/**
	 * Crea un usuario principal.
	 * 
	 * @param user
	 * @return UserPrincipal
	 */
	public static UserPrincipal create(User user) {
		
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
        	new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
        
        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
