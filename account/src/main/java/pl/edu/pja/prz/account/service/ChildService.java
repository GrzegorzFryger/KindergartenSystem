package pl.edu.pja.prz.account.service;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.StudyPeriod;

import java.util.Optional;
import java.util.UUID;

public interface ChildService {
	Child createChild(Long boroughId, Address address, FullName fullName, String pesel,
	                  StudyPeriod studyPeriod);

	Child createChild(Long boroughId, Address address, Age age, FullName fullName, Gender gender,
	                  StudyPeriod studyPeriod);

	Child getChildById(UUID id);

	Optional<Child> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street)
			throws IllegalStateException;
}
