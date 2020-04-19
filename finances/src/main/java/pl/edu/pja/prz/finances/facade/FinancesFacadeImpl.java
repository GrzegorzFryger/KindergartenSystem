package pl.edu.pja.prz.finances.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.GuardianFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.finances.model.dto.AccountNumberDto;
import pl.edu.pja.prz.finances.model.dto.Balance;
import pl.edu.pja.prz.finances.service.AccountNumberService;
import pl.edu.pja.prz.finances.service.BalanceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FinancesFacadeImpl implements FinancesFacade {
    private final BalanceService balanceService;
    private final GuardianFacade guardianFacade;
    private final AccountNumberService accountNumberService;

    @Autowired
    public FinancesFacadeImpl(BalanceService balanceService, GuardianFacade guardianFacade,
                              AccountNumberService accountNumberService) {
        this.balanceService = balanceService;
        this.guardianFacade = guardianFacade;
        this.accountNumberService = accountNumberService;
    }

    @Override
    public Balance getBalance(UUID childId) {
        return balanceService.getBalance(childId);
    }

    @Override
    public List<Balance> getBalancesForAllChildren(UUID guardianId) {
        Set<ChildDto> childDtos = guardianFacade.findAllGuardianChildren(guardianId);
        List<Balance> childBalances = new ArrayList<>();
        for (ChildDto dto : childDtos) {
            childBalances.add(getBalance(dto.getId()));
        }
        return childBalances;
    }

    @Override
    public Balance getBalanceForAllChildren(UUID guardianId) {
        Set<ChildDto> childDtos = guardianFacade.findAllGuardianChildren(guardianId);
        List<UUID> childIdList = new ArrayList<>();
        for (ChildDto dto : childDtos) {
            childIdList.add(dto.getId());
        }
        return balanceService.getBalanceForAllChildren(childIdList, guardianId);
    }

    @Override
    public void increaseBalance(UUID childId, BigDecimal amount, String title) {
        balanceService.increaseBalance(childId, amount, title);
    }

    @Override
    public void decreaseBalance(UUID childId, BigDecimal amount, String title) {
        balanceService.decreaseBalance(childId, amount, title);
    }

    @Override
    public void applyBalanceCorrection(UUID childId, BigDecimal amount, String title) {
        balanceService.applyBalanceCorrection(childId, amount, title);
    }

    @Override
    public AccountNumberDto getAccountNumber(UUID childId) {
        return accountNumberService.getAccountNumber(childId);
    }
}
