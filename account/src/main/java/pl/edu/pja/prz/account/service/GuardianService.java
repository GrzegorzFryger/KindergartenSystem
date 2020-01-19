package pl.edu.pja.prz.account.service;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GuardianService {
	Guardian createGuardianAccount(Person person, String email);

	Guardian createGuardianAccount(Address address, FullName fullName, Phone phone,
	                               String email);

	Guardian getGuardianById(UUID id);

	List<Guardian> getAllGuardians();

	void appendChildrenToGuardian(UUID childId, Set<UUID> setGuardianId);

	Set<Child> getAllChildren(UUID id);

	Optional<Guardian> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street) throws IllegalStateException;
}
