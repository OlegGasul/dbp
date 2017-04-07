package de.mexchange.packagingdb.security;

import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.domain.lcp.UserStatus;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Garik on 4/30/16.
 */
public class SessionUser extends User implements UserDetails {

	public SessionUser(User user) {
		super(user);
	}

	@Override
	public String getUsername() {
		return getEmail();
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
		return getStatus() == UserStatus.ACTIVE;
	}
}
