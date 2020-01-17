package pl.edu.pja.prz.finances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.repository.BalanceRepository;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static pl.edu.pja.prz.commons.util.BigDecimalUtils.*;

@Service
public class BalanceServiceImpl implements BalanceService {
    private static final String BALANCE = "Balance";
    private final BalanceRepository repository;
    private final BalanceHistoryService historyService;

    @Autowired
    public BalanceServiceImpl(BalanceRepository repository, BalanceHistoryService historyService) {
        this.repository = repository;
        this.historyService = historyService;
    }

    @Override
    public Balance getBalance(UUID childId) {
        Optional<Balance> result = repository.getByChildId(childId);
        if (result.isEmpty()) {
            throw new ElementNotFoundException(BALANCE, childId.toString());
        }
        return result.get();
    }

    @Override
    public List<Balance> getBalances(UUID guardianId) {
        return repository.getAllByGuardianId(guardianId);
    }

    @Override
    public Balance increaseBalance(UUID childId, BigDecimal amount) {
        if (isPositive(amount)) {
            Balance balance = getBalance(childId);
            BigDecimal amountBeforeChange = balance.getAmount();
            balance.setAmount(sum(balance.getAmount(), amount));
            saveBalance(balance);
            historyService.saveBalanceInHistory(childId, amountBeforeChange, amount);
            return balance;
        } else {
            throw new BusinessException("Attempt was made to increase balance when providing negative amount: " + amount);
        }
    }

    @Override
    public Balance decreaseBalance(UUID childId, BigDecimal amount) {
        if (isNegative(amount)) {
            Balance balance = getBalance(childId);
            BigDecimal amountBeforeChange = balance.getAmount();
            balance.setAmount(sum(balance.getAmount(), amount));
            saveBalance(balance);
            historyService.saveBalanceInHistory(childId, amountBeforeChange, amount);
            return balance;
        } else {
            throw new BusinessException("Attempt was made to decrease balance when providing positive amount: " + amount);
        }
    }

    @Override
    public void saveBalance(Balance balance) {
        repository.save(balance);
    }
}
