package pl.edu.pja.prz.finances.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.service.BalanceHistoryService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class FinancesFacadeImpl implements FinancesFacade {
    private final BalanceService balanceService;
    private final BalanceHistoryService balanceHistoryService;

    @Autowired
    public FinancesFacadeImpl(BalanceService balanceService, BalanceHistoryService balanceHistoryService) {
        this.balanceService = balanceService;
        this.balanceHistoryService = balanceHistoryService;
    }

    @Override
    public Balance getBalance(UUID childId) {
        return balanceService.getBalance(childId);
    }

    @Override
    public List<Balance> getBalances(UUID guardianId) {
        return balanceService.getBalances(guardianId);
    }

    @Override
    public Balance increaseBalance(UUID childId, BigDecimal amount) {
        return balanceService.increaseBalance(childId, amount);
    }

    @Override
    public Balance decreaseBalance(UUID childId, BigDecimal amount) {
        return balanceService.decreaseBalance(childId, amount);
    }

    @Override
    public void saveBalanceInHistory(UUID childId, BigDecimal oldBalance, BigDecimal change) {
        balanceHistoryService.saveBalanceInHistory(childId, oldBalance, change);
    }
}
