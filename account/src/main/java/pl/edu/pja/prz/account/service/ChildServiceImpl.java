package pl.edu.pja.prz.account.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.ChildBuilder;
import pl.edu.pja.prz.account.model.Child_;
import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.PeselService;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ChildServiceImpl extends GenericService<ChildRepository,Child,UUID> {
	private static final Logger logger = LoggerFactory.logger(ChildServiceImpl.class);
	private static final String CHILD = "Child";
	private static final ChildStatus CHILDSTATUS = ChildStatus.NEW;
	private final ChildRepository childRepository;
	private final PeselService peselService;

	public ChildServiceImpl(ChildRepository repository, ChildRepository childRepository, PeselService peselService) {
		super(repository);
		this.childRepository = childRepository;
		this.peselService = peselService;
	}

	public Child createChild(Address address, FullName fullName, String pesel,
	                         StudyPeriod studyPeriod) {
		return createChildPriv(address, fullName, pesel, studyPeriod);
	}


	public Child createChild(Address address, Age age, FullName fullName, Gender gender,
	                         StudyPeriod studyPeriod) {
		//todo write condition for children without pesel number
		return createChildPriv(address, age, fullName, gender, "NOT_SET", studyPeriod);
	}


	public Optional<Child> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street) {
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

	private Child createChildPriv(Address address, FullName fullName, String pesel,
	                              StudyPeriod studyPeriod) {
		return createChildPriv(address,
				new Age(peselService.extractDateOfBirth(pesel)),
				fullName,
				peselService.extractGender(pesel),
				pesel,
				studyPeriod
		);
	}

	private Child createChildPriv(Address address, Age age, FullName fullName, Gender gender,
	                              String pesel, StudyPeriod studyPeriod) {
		var child = ChildBuilder.aChild()
				.withAddress(address)
				.withAge(age)
				.withFullName(fullName)
				.withGender(gender)
				.withChildStatuses(Set.of(CHILDSTATUS))
				.withPeselNumber(pesel)
				.withStudyPeriod(studyPeriod).build();

		return childRepository.save(child);
	}


}
