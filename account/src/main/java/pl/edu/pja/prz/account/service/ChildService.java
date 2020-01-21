package pl.edu.pja.prz.account.service;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Optional;
import java.util.UUID;

public interface ChildService {

	Child createChild(Address address, FullName fullName, String pesel,
	                  StudyPeriod studyPeriod);

	Child getChildById(UUID id);

	Child createChild(Address address, Age age, FullName fullName, Gender gender,
	                  StudyPeriod studyPeriod);

	Child updateChild(Child child);

	Optional<Child> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street)
			throws IllegalStateException;
}
