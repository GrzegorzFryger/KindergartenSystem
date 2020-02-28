package pl.edu.pja.prz.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.AccountCredentialFacade;
import pl.edu.pja.prz.account.facade.dto.AccountCredentialDto;
import pl.edu.pja.prz.account.model.enums.AccountStatus;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private final AccountCredentialFacade accountFacade;

	@Autowired
	public JwtUserDetailsService(AccountCredentialFacade accountService) {
		this.accountFacade = accountService;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return accountFacade
				.findByEmail(s)
				.map(this::convert)
				.orElseThrow(() -> new UsernameNotFoundException(s));
	}

	private UserDetails convert(AccountCredentialDto accountDto) {
		var userDetails = new JwtUserDetails();

		userDetails.setEmail(accountDto.getEmail());
		userDetails.setPassword(accountDto.getPassword());
		userDetails.setEnabled(checkAccountStatus(accountDto));
		userDetails.setAuthorities(getAuthorities(accountDto));

		return userDetails;
	}

	private Collection<SimpleGrantedAuthority> getAuthorities(AccountCredentialDto accountDto) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		accountDto.getRoles().forEach(roleDto -> authorities.add(new SimpleGrantedAuthority(roleDto.getName())));
		return authorities;
	}

	private boolean checkAccountStatus(AccountCredentialDto accountDto) {
		return accountDto.getStatus().equals(AccountStatus.ACTIVE);
	}
}
