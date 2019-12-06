package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;

import java.util.Set;
import java.util.UUID;

@Component
public class GuardianService extends AccountServiceImpl<GuardianRepository, Guardian>
		implements AccountService<GuardianRepository, Guardian> {

	private final GuardianRepository guardianRepository;
	private final ChildRepository childRepository;

	@Autowired
	protected GuardianService(GuardianRepository accountRepository, PasswordManager passwordManager,
	                          ChildRepository childRepository) {
		super(accountRepository, passwordManager);
		this.guardianRepository = accountRepository;
		this.childRepository = childRepository;
	}

	public Set<Child> getAllChildren(UUID id ) {
		return guardianRepository.findById(id)
				.map(Guardian::getChildren)
				.orElseThrow(()-> {throw new IllegalArgumentException("Not found");});
	}

}
