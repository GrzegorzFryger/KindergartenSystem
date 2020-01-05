package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
class ChildServiceImpl implements ChildService {
	private static final ChildStatus CHILDSTATUS = ChildStatus.NEW;
	private final ChildRepository childRepository;
	private final PeselService peselService;
	private final BoroughService boroughService;

	@Autowired
	public ChildServiceImpl(ChildRepository childRepository, PeselService peselService, BoroughService boroughService) {
		this.childRepository = childRepository;
		this.peselService = peselService;
		this.boroughService = boroughService;
	}


	@Override
	public Child createChild(Long boroughId, Address address, FullName fullName, String pesel,
	                         StudyPeriod studyPeriod) {
		var borough = boroughService.findBorough(boroughId).orElseThrow(() -> {
			throw new IllegalArgumentException("Borough with id not exist: " + boroughId);
		});

		var child = createChild(address, borough, fullName, pesel, studyPeriod);
		boroughService.addChildToBorough(child, borough);
		return child;

	}

	@Override
	public Child createChild(Long boroughId, Address address, Age age, FullName fullName, Gender gender,
	                         StudyPeriod studyPeriod) {
		var borough = boroughService.findBorough(boroughId).orElseThrow(() -> {
			throw new IllegalArgumentException("Borough with id not exist: " + boroughId);
		});

		//todo write condition for children without pesel number
		var child = createChild(address, age, borough, fullName, gender, "NOT_SET", studyPeriod);
		boroughService.addChildToBorough(child, borough);
		return child;

	}

	@Override
	public Child getChildById(UUID id) {
		return childRepository.findById(id).orElseThrow(
				() -> {
					throw new IllegalArgumentException("Not found child with id " + id);
				});
	}

	private Child createChild(Address address, Borough borough, FullName fullName, String pesel,
	                          StudyPeriod studyPeriod) {
		return createChild(address,
				new Age(peselService.extractDateOfBirth(pesel)),
				borough,
				fullName,
				peselService.extractGender(pesel),
				pesel,
				studyPeriod
		);
	}

	private Child createChild(Address address, Age age, Borough borough, FullName fullName, Gender gender,
	                          String pesel,
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




}
