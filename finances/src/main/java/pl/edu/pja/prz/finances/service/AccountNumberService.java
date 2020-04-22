package pl.edu.pja.prz.finances.service;

import pl.edu.pja.prz.finances.model.dto.AccountNumberDto;

import java.util.UUID;

public interface AccountNumberService {
    AccountNumberDto getAccountNumber(UUID childId);
}
