package pl.edu.pja.prz.finances.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FinancesFacadeImpl implements FinancesFacade {
    private final BalanceService balanceService;

    @Autowired
    public FinancesFacadeImpl(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public Balance getBalance(UUID childId) {
        return balanceService.getBalance(childId);
    }

    @Override
    public void increaseBalance(UUID childId, BigDecimal amount, String title) {
        balanceService.increaseBalance(childId, amount, title);
    }

    @Override
    public void decreaseBalance(UUID childId, BigDecimal amount, String title) {
        balanceService.decreaseBalance(childId, amount, title);
    }
}
