package pl.edu.pja.prz.account.service;

import org.jooq.lambda.Unchecked;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.event.AccountEventPublisher;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Guardian_;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address_;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.FullName_;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class GuardianService extends BasicAccountService<GuardianRepository, Guardian> {
    private static final String USER = "User";
    private final ChildService childService;
    private final AccountEventPublisher accountEventPublisher;

    public GuardianService(GuardianRepository repository, AccountFactory accountFactory, PasswordManager passwordManager,
                           RoleService roleService, ChildService childService, AccountEventPublisher accountEventPublisher) {
        super(repository, accountFactory, passwordManager, roleService);
        this.childService = childService;
        this.accountEventPublisher = accountEventPublisher;
    }

    public Guardian createGuardianAccount(Person person, String email) {
        return persistStandardAccount(
                person.getAddress(),
                person.getFullName(),
                person.getPhoneNumber(),
                email,
                Guardian.class
        );
    }

    public Guardian appendChildrenToGuardian(UUID childId, UUID guardianId) {
        var child = childService.getById(childId);

        return repository.findById(guardianId)
                .map(guardian -> {
                    guardian.addChild(child);
                    return repository.save(guardian);
                })
                .map(guardian -> {
                    this.accountEventPublisher.appendChildToGuardianEvent(
                            new GuardianChildDependency(guardian.getId(), child.getId(), child.getFullName())
                    );
                    return guardian;
                })
                .orElseThrow(() -> {
                    throw new ElementNotFoundException(guardianId);
                });
    }

    public Set<Child> getAllChildren(UUID guardianId) {
        return repository.findById(guardianId)
                .map(Guardian::getChildren)
                .orElseThrow(() -> {
                    throw new ElementNotFoundException(guardianId);
                });
    }

    /**
     * @param fullName
     * @param street   optional param
     * @return Optional<Guardian> return guardian when find only one user, otherwise null
     * @throws Exception - when find more then one Guardian
     */

    public Optional<Guardian> findByFullNameOrAddressReadOnly(FullName fullName, @Nullable String street) throws MoreThanOneElement {
        if (street == null) {
            return repository.findReadOnly((root, query, cb) ->
                    cb.equal(root.get(Guardian_.fullName), fullName), Guardian.class)
                    .stream()
                    .reduce((u, v) -> {
                        Unchecked.throwChecked(new MoreThanOneElement(fullName));
                        return null;
                    });
        } else {
            return repository.findReadOnly((root, query, cb) ->
                            cb.and(
                                    cb.equal(root.get(Guardian_.fullName), fullName),
                                    cb.like(root.get(Guardian_.address).get(Address_.streetNumber), street)
                            )
                    , Guardian.class)
                    .stream()
                    .reduce((u, v) -> {
                        Unchecked.throwChecked(new MoreThanOneElement(fullName));
                        return null;
                    });
        }
    }

    public List<Guardian> findByFullName(FullName fullName) {
        return repository.findReadOnly((root, query, cb) ->
                cb.and(
                        cb.like(root.get(Guardian_.FULL_NAME).get(FullName_.NAME), fullName.getName()),
                        cb.like(root.get(Guardian_.FULL_NAME).get(FullName_.SURNAME), fullName.getSurname())
                ), Guardian.class);
    }

    public List<Guardian> findByChildId(UUID chidlId) {
        return this.repository.findByChildId(chidlId);
    }

}
