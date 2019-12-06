package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.ChildBuilder;
import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.PeselService;

import java.util.Set;
import java.util.UUID;

@Component
class ChildService {
	private final ChildStatus CHILDSTATUS = ChildStatus.NEW;

	private final ChildRepository childRepository;
	private final PeselService peselService;

	@Autowired
	public ChildService(ChildRepository childRepository, PeselService peselService) {
		this.childRepository = childRepository;
		this.peselService = peselService;
	}

	Child createChild(Address address, Borough borough, FullName fullName, String pesel, StudyPeriod studyPeriod) {
		return createChild( address,
				new Age(peselService.extractDateOfBirth(pesel)),
				borough,
				fullName,
				peselService.extractGender(pesel),
				pesel,
				studyPeriod
		);
	}

	Child createChild(Address address, Age age, Borough borough, FullName fullName, Gender gender, String pesel,
	                  StudyPeriod studyPeriod) {
		var child = ChildBuilder.aChild()
				.withAddress(address)
				.withAge(age)
				.withBorough(borough)
				.withFullName(fullName)
				.withGender(gender)
				.withChildStatuses(Set.of(CHILDSTATUS))
				.withPeselNumber(pesel)
				.withStudyPeriod(studyPeriod).build();

		return childRepository.save(child);
	}

	public Child getChildById(UUID id) {
		return childRepository.findById(id).orElseThrow(
				() -> {throw new IllegalArgumentException("Not found child with id " + id);});
	}



}
