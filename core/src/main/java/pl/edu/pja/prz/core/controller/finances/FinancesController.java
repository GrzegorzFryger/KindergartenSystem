package pl.edu.pja.prz.core.controller.finances;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.finances.model.dto.Balance;

import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ANY_ROLE;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_FINANCES;

@RestController
@RequestMapping(API_FINANCES)
public class FinancesController {
    private final FinancesFacade facade;

    public FinancesController(FinancesFacade facade) {
        this.facade = facade;
    }

    @GetMapping("balance/{childId}")
    @PreAuthorize(HAS_ANY_ROLE)
    public Balance getBalance(@PathVariable UUID childId) {
        return facade.getBalance(childId);
    }

    @GetMapping("balance/guardian/{guardianId}")
    @PreAuthorize(HAS_ANY_ROLE)
    public Balance getBalanceForAllChildren(@PathVariable UUID guardianId) {
        return facade.getBalanceForAllChildren(guardianId);
    }
}
