package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.BaseEntity;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.UUID;


public abstract class BasicAccountService<T extends BasicAccountRepository<E, UUID>, E extends Account & BaseEntity<UUID>>
        extends GenericService<T, E, UUID> {
    protected final AccountFactory accountFactory;
    protected final PasswordManager passwordManager;
    protected final RoleService roleService;

    public BasicAccountService(T repository, AccountFactory accountFactory, PasswordManager passwordManager,
                               RoleService roleService) {
        super(repository);
        this.accountFactory = accountFactory;
        this.passwordManager = passwordManager;
        this.roleService = roleService;
    }

    protected E persistStandardAccount(Address address, FullName fullName, Phone phone,
                                       String email, Class<E> tClass) {
        repository.findByEmailAndFullName(email, fullName).ifPresent(account -> {
            throw new IllegalArgumentException("Person exist with id: " + account.getId());
        });

        var result = repository.save(
                accountFactory.createStandardAccount(
                        new Person(address, fullName, phone),
                        new Password(passwordManager.generateEncodeRandomPassword()),
                        email,
                        tClass
                )
        );
        roleService.persistRoleFromUser(result);
        return result;
    }

    @Override
    public E update(E updated) {
        return repository.findById(updated.getId()).map(account -> {
                    if (updated.getFullName() != null) {
                        account.setFullName(updated.getFullName());
                    }
                    if (updated.getAddress() != null) {
                        account.setAddress(updated.getAddress());
                    }
                    if (updated.getPhoneNumber() != null) {
                        account.setPhoneNumber(updated.getPhoneNumber());
                    }
                    if (updated.getEmail() != null) {
                        account.setEmail(updated.getEmail());
                    }
                    if (updated.getAccountStatus() != null) {
                        account.setAccountStatus(updated.getAccountStatus());
                    }
                    return repository.save(account);
                }
        ).orElseThrow(() -> new ElementNotFoundException(updated.getId()));

    }
}
