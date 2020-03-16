package pl.edu.pja.prz.finances.service;

import pl.edu.pja.prz.finances.model.BalanceHistory;
import pl.edu.pja.prz.finances.model.enums.OperationType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BalanceHistoryService {
    void saveBalanceInHistory(UUID childId, BigDecimal amountOfChange, String title, OperationType operationType);

    List<BalanceHistory> getAllHistoryRecordsForChild(UUID childId);
}
