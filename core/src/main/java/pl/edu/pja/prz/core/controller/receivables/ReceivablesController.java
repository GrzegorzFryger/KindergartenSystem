package pl.edu.pja.prz.core.controller.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ANY_ROLE;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@RestController
@RequestMapping(API_RECEIVABLES)
public class ReceivablesController {
    private final ReceivablesFacade receivablesFacade;

    @Autowired
    public ReceivablesController(ReceivablesFacade receivablesFacade) {
        this.receivablesFacade = receivablesFacade;
    }

    @GetMapping("payments/child/{childId}")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<IncomingPaymentDto> getAllIncomingPaymentsForChild(@PathVariable UUID childId) {
        return receivablesFacade.getAllIncomingPaymentsByChildId(childId);
    }

    @GetMapping("payments/child/{childId}/{from}/{to}")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<IncomingPaymentDto> getAllIncomingPaymentsForChild(@PathVariable UUID childId,
                                                                   @PathVariable String from,
                                                                   @PathVariable String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return receivablesFacade.getAllIncomingPaymentsByChildId(childId, fromDate, toDate);
    }

    @GetMapping("payments/guardian/{guardianId}")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<IncomingPaymentDto> getAllIncomingPaymentsForGuardian(@PathVariable UUID guardianId) {
        return receivablesFacade.getAllIncomingPaymentsByGuardianId(guardianId);
    }

    @GetMapping("payments/guardian/{guardianId}/{from}/{to}")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<IncomingPaymentDto> getAllIncomingPaymentsForGuardian(@PathVariable UUID guardianId,
                                                                      @PathVariable String from,
                                                                      @PathVariable String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return receivablesFacade.getAllIncomingPaymentsByGuardianId(guardianId, fromDate, toDate);
    }

    @GetMapping("payments/mappings/{guardianId}")
    @PreAuthorize(HAS_ANY_ROLE)
    public List<TransactionMapping> getAllPaymentMappingsForGuardian(@PathVariable UUID guardianId) {
        return receivablesFacade.getAllMappingsForGuardian(guardianId);
    }

    @PostMapping(value = "transactions/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<Transaction> importTransactions(@RequestBody MultipartFile input,
                                                @RequestHeader(name = "Input-Encoding", required = false) String encoding)
            throws IOException {
        return receivablesFacade.getTransactionListFromCsv(input, encoding);
    }
}
