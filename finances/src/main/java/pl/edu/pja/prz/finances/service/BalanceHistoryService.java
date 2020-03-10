package pl.edu.pja.prz.finances.service;

import pl.edu.pja.prz.finances.model.BalanceHistory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BalanceHistoryService {
    void saveBalanceInHistory(UUID childId, BigDecimal amountOfChange, String title);

    List<BalanceHistory> getAllHistoryRecordsForChild(UUID childId);
}
