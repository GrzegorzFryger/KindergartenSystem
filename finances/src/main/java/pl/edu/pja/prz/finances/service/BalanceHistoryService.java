package pl.edu.pja.prz.finances.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceHistoryService {
    void saveBalanceInHistory(UUID childId, BigDecimal oldBalance, BigDecimal change);
}
