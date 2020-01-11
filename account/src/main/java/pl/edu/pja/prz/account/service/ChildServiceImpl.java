package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.*;
import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.PeselService;

import java.util.Optional;
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
		var borough = boroughService.findBorough(boroughId);

		var child = createChild(address, borough, fullName, pesel, studyPeriod);
		boroughService.addChildToBorough(child, borough);
		return child;

	}

	@Override
	public Child createChild(Long boroughId, Address address, Age age, FullName fullName, Gender gender,
	                         StudyPeriod studyPeriod) {
		var borough = boroughService.findBorough(boroughId);

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

	@Override
	public Optional<Child> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street)
			throws IllegalStateException {
		if (street == null) {
			return childRepository.findReadOnly((root, query, cb) ->
					cb.equal(root.get(Child_.fullName), fullName), Child.class)
					.stream()
					.reduce((u, v) -> {
						throw new IllegalStateException("More than one child found");
					});
		} else {
			return childRepository.findReadOnly((root, query, cb) ->
							cb.and(
									cb.equal(root.get(Child_.fullName), fullName),
									cb.like(root.get(Child_.address).get(Address_.streetNumber), street)
							)
					, Child.class)
					.stream()
					.reduce((u, v) -> {
						throw new IllegalStateException("More than one child found");
					});
		}
	}


}
