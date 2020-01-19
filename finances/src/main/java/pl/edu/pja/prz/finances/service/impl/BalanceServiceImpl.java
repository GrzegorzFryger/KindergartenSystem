package pl.edu.pja.prz.finances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.repository.BalanceRepository;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepository repository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Balance getBalance(UUID childId) {
        return null;
    }

    @Override
    public List<Balance> getBalances(UUID guardianId) {
        return null;
    }

    @Override
    public Balance increaseBalance(UUID childId, BigDecimal amount) {
        return null;
    }

    @Override
    public Balance decreaseBalance(UUID childId, BigDecimal amount) {
        return null;
    }
}
