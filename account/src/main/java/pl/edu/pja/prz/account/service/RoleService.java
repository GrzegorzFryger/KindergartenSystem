package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.repository.RoleRepository;

@Service
public class RoleService {
	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public void persistRoleFromUser(Account account) {
		account.getRoles().forEach(x ->
				roleRepository.findByName(x.getName())
						.ifPresentOrElse(roleRepository::save, () -> roleRepository.save(x))
		);
	}
}
