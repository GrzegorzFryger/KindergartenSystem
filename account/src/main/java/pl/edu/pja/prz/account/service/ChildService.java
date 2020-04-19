package pl.edu.pja.prz.account.service;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.jooq.lambda.Unchecked;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Child_;
import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.ChildBuilder;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.FullName_;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ChildService extends GenericService<ChildRepository, Child, UUID> {
    private static final Logger childServiceLogger = LoggerFactory.logger(ChildService.class);

    private static final String CHILD_WITHOUT_PESEL = "NOT_SET";
    private static final ChildStatus CHILDSTATUS = ChildStatus.NEW;
    private final ChildRepository childRepository;
    private final PeselService peselService;

    public ChildService(ChildRepository repository, PeselService peselService) {
        super(repository);
        this.childRepository = repository;
        this.peselService = peselService;
    }


    public Child createChild(Address address, FullName fullName, String pesel,
                             StudyPeriod studyPeriod) {
        return createChildBasedOnPesel(address, fullName, pesel, studyPeriod);
    }


    public Child createChild(Address address, Age age, FullName fullName, Gender gender,
                             StudyPeriod studyPeriod) {
        //todo write condition for children without pesel number
        return createChildWithAllData(address, age, fullName, gender, CHILD_WITHOUT_PESEL, studyPeriod);
    }


    public Optional<Child> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street) throws MoreThanOneElement {
        if (street == null) {
            return childRepository.findReadOnly((root, query, cb) ->
                    cb.equal(root.get(Child_.fullName), fullName), Child.class)
                    .stream()
                    .reduce((u, v) -> {
                        Unchecked.throwChecked(new MoreThanOneElement(fullName));
                        return null;
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
                        Unchecked.throwChecked(new MoreThanOneElement(fullName));
                        return null;
                    });
        }
    }

    public List<Child> findByFullNameReadOnly(FullName fullName) {
        return repository.findReadOnly((root, query, cb) ->
        {
            var nameString = "%" + fullName.getName() + "%";
            var surnameString = "%" + fullName.getSurname() + "%";
            return cb.and(
                    cb.like(root.get(Child_.FULL_NAME).get(FullName_.NAME), nameString),
                    cb.like(root.get(Child_.FULL_NAME).get(FullName_.SURNAME), surnameString)
            );
        }, Child.class);


    }

    private Child createChildBasedOnPesel(Address address, FullName fullName, String pesel,
                                          StudyPeriod studyPeriod) {
        var gender = peselService.extractGender(pesel);
        var age = new Age(peselService.extractDateOfBirth(pesel));

        return createChildWithAllData(address,
                age,
                fullName,
                gender,
                pesel,
                studyPeriod
        );
    }

    private Child createChildWithAllData(Address address, Age age, FullName fullName, Gender gender,
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
