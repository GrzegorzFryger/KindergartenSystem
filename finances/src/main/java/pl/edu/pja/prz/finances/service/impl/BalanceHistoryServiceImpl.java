package pl.edu.pja.prz.finances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.repository.BalanceHistoryRepository;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BalanceHistoryServiceImpl implements BalanceHistoryService {
    private final BalanceHistoryRepository repository;

    @Autowired
    public BalanceHistoryServiceImpl(BalanceHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveBalanceInHistory(UUID childId, BigDecimal oldBalance, BigDecimal change) {

    }
}
