package pl.edu.pja.prz.finances.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.AccountNumber;
import pl.edu.pja.prz.finances.model.dto.AccountNumberDto;
import pl.edu.pja.prz.finances.repository.AccountNumberRepository;
import pl.edu.pja.prz.finances.service.AccountNumberService;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountNumberServiceImpl implements AccountNumberService {
    /**
     * Since we support only one account - for now it's ID is hardcoded here
     */
    private static final Long ACCOUNT_ID = 1L;

    private final AccountNumberRepository accountNumberRepository;

    public AccountNumberServiceImpl(AccountNumberRepository accountNumberRepository) {
        this.accountNumberRepository = accountNumberRepository;
    }

    @Override
    public AccountNumberDto getAccountNumber(UUID childId) {
        AccountNumberDto dto = new AccountNumberDto();
        Optional<AccountNumber> accountNumber = accountNumberRepository.findById(ACCOUNT_ID);
        accountNumber.ifPresent(number -> dto.setAccountNumber(number.getAccountNumber()));
        return dto;
    }
}
