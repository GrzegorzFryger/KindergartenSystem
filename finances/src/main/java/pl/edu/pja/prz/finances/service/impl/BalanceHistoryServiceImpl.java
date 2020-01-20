package pl.edu.pja.prz.finances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.builder.BalanceHistoryBuilder;
import pl.edu.pja.prz.finances.repository.BalanceHistoryRepository;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BalanceHistoryServiceImpl implements BalanceHistoryService {
    private final BalanceHistoryRepository repository;

    @Autowired
    public BalanceHistoryServiceImpl(BalanceHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveBalanceInHistory(UUID childId, BigDecimal oldBalance, BigDecimal amountOfChange, String title) {
        BalanceHistory balanceHistory = new BalanceHistoryBuilder()
                .withChildId(childId)
                .withBalanceBeforeChange(oldBalance)
                .withAmountOfChange(amountOfChange)
                .withTitle(title)
                .build();

        repository.save(balanceHistory);
    }

    @Override
    public List<BalanceHistory> getAllHistoryRecordsForChild(UUID childId) {
        return repository.findAllByChildId(childId);
    }
}
