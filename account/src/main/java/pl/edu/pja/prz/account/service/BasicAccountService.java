package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.BasicAccountRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.BaseEntity;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;
import pl.edu.pja.prz.mail.facade.MailFacade;
import pl.edu.pja.prz.mail.model.BaseMail;

import java.util.Optional;
import java.util.UUID;


public abstract class BasicAccountService<T extends BasicAccountRepository<E, UUID>, E extends Account & BaseEntity<UUID>>
        extends GenericService<T, E, UUID> {
    private static final String CLIENT_HTTP_ADDRESS_PROPERTIES = "https://localhost:4200/auth/authenticate?token=";
    private static final String ACCOUNT_CREATE_EMAIL_SUBJECT = "Aktywacja konta";
    protected final AccountFactory accountFactory;
    protected final PasswordManager passwordManager;
    protected final RoleService roleService;
    protected final MailFacade mailFacade;
    protected final ActivateTokenService activateTokenService;

    public BasicAccountService(T repository, AccountFactory accountFactory, PasswordManager passwordManager,
                               RoleService roleService, MailFacade mailFacade, ActivateTokenService activateTokenService) {
        super(repository);
        this.accountFactory = accountFactory;
        this.passwordManager = passwordManager;
        this.roleService = roleService;
        this.mailFacade = mailFacade;
        this.activateTokenService = activateTokenService;
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

        this.mailFacade.sendEmail(
                prepareRegisterEmail(
                        result.getEmail(),
                        result.getPassword().getPassword())
        );

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

    private BaseMail prepareRegisterEmail(String email, String password) {
        return Optional.ofNullable(this.activateTokenService.generateToken(email, password)).map(token -> {
            String content = CLIENT_HTTP_ADDRESS_PROPERTIES + token;
            BaseMail baseMail = new BaseMail();
            baseMail.setTo(email);
            baseMail.setSubject(ACCOUNT_CREATE_EMAIL_SUBJECT);
            baseMail.setContent(content);
            return baseMail;
        }).orElseThrow(() -> {
            throw new BusinessException("Cant prepare activate token");
        });
    }
}
