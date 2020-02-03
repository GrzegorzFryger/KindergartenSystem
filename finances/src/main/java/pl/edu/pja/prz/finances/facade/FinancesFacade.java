
package pl.edu.pja.prz.finances.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.Balance;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class FinancesFacade {
    private final BalanceService balanceService;

    @Autowired
    public FinancesFacade(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public Balance getBalance(UUID childId) {
        return balanceService.getBalance(childId);
    }

    public List<Balance> getBalances(UUID guardianId) {
        return balanceService.getBalances(guardianId);
    }

    public void increaseBalance(UUID childId, BigDecimal amount, String title) {
        balanceService.increaseBalance(childId, amount, title);
    }

    public void decreaseBalance(UUID childId, BigDecimal amount, String title) {
        balanceService.decreaseBalance(childId, amount, title);
    }
}