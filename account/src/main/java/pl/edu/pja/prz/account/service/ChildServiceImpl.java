package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
class ChildServiceImpl implements ChildService {
	private static final String CHILD = "Child";
	private static final ChildStatus CHILDSTATUS = ChildStatus.NEW;
	private final ChildRepository childRepository;
	private final PeselService peselService;

	@Autowired
	public ChildServiceImpl(ChildRepository childRepository, PeselService peselService) {
		this.childRepository = childRepository;
		this.peselService = peselService;
	}

	@Override
	public Child getChildById(UUID id) {
		return childRepository.findById(id).orElseThrow(
				() -> {
					throw new ElementNotFoundException(CHILD, id);
				});
	}

	@Override
	public Child createChild(Address address, FullName fullName, String pesel,
	                         StudyPeriod studyPeriod) {
		return createChildPriv(address, fullName, pesel, studyPeriod);
	}

	@Override
	public Child createChild(Address address, Age age, FullName fullName, Gender gender,
	                         StudyPeriod studyPeriod) {
		//todo write condition for children without pesel number
		return createChildPriv(address, age, fullName, gender, "NOT_SET", studyPeriod);
	}

	@Override
	public Child updateChild(Child child) {
		return childRepository.findById(child.getId()).map(childToUpdate -> {
					updateNotNullFields(childToUpdate, child);

					return childRepository.save(childToUpdate);
				}
		).orElseThrow(() -> new ElementNotFoundException(CHILD, child.getId()));

	}

	private void updateNotNullFields(Child oldChild, Child newChild) {
		Arrays.stream(newChild.getClass().getDeclaredFields())
				.forEach(a -> {
					a.setAccessible(true);
					try {
						Optional.ofNullable(a.get(newChild))
								.ifPresent(value -> {
									try {
										a.set(oldChild, a.get(newChild));

									} catch (IllegalAccessException e) {
										e.printStackTrace();
									}
								});
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				});
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
