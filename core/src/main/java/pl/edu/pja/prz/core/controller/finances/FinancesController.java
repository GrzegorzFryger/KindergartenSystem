package pl.edu.pja.prz.core.controller.finances;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.finances.model.Balance;

import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_FINANCES;

@RestController
@RequestMapping(API_FINANCES)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class FinancesController {
    private final FinancesFacade facade;

    public FinancesController(FinancesFacade facade) {
        this.facade = facade;
    }

    @GetMapping("balance/{childId}")
    public Balance getBalance(@PathVariable UUID childId) {
        return facade.getBalance(childId);
    }

    @GetMapping("balance-list/{guardianId}")
    public List<Balance> getBalances(@PathVariable UUID guardianId) {
        return facade.getBalances(guardianId);
    }
}
