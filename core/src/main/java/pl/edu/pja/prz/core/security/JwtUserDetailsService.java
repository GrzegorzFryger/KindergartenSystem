package pl.edu.pja.prz.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.AccountFacade;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.model.enums.AccountStatus;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private final AccountFacade accountFacade;

	@Autowired
	public JwtUserDetailsService(AccountFacade accountService) {
		this.accountFacade = accountService;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return convert(accountFacade.findAccountByEmail(s));
	}

	private UserDetails convert(AccountDto accountDto) {
		var userDetails = new JwtUserDetails();

		userDetails.setEmail(accountDto.getEmail());
		userDetails.setPassword(accountDto.getPassword());
		userDetails.setEnabled(checkAccountStatus(accountDto));
		userDetails.setAuthorities(getAuthorities(accountDto));

		return userDetails;
	}

	private Collection<SimpleGrantedAuthority> getAuthorities(AccountDto accountDto) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		accountDto.getRoles().forEach(roleDto -> authorities.add(new SimpleGrantedAuthority(roleDto.getName())));
		return authorities;
	}

	private boolean checkAccountStatus(AccountDto accountDto) {
		return accountDto.getStatus().equals(AccountStatus.ACTIVE);
	}
}
