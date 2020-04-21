package pl.edu.pja.prz.receivables.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.repository.CashPaymentRepository;
import pl.edu.pja.prz.receivables.service.CashPaymentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.util.BigDecimalUtils.isPositive;

@Service
public class CashPaymentServiceImpl implements CashPaymentService {
    private static final String CASH_PAYMENT = "Cash Payment";

    private final FinancesFacade facade;
    private final CashPaymentRepository repository;

    @Autowired
    public CashPaymentServiceImpl(FinancesFacade facade, CashPaymentRepository repository) {
        this.facade = facade;
        this.repository = repository;
    }

    @Override
    public CashPayment getCashPayment(Long id) {
        return repository.findById(id).orElseThrow(() -> new ElementNotFoundException(CASH_PAYMENT, id));
    }

    @Override
    public List<CashPayment> getAllCashPayments() {
        return repository.findAll();
    }

    @Override
    public List<CashPayment> getAllCashPaymentsByChildId(UUID childId) {
        return repository.findAllByChildId(childId);
    }

    @Override
    public List<CashPayment> getAllCashPaymentsByGuardianId(UUID guardianId) {
        return repository.findAllByGuardianId(guardianId);
    }

    @Override
    public List<CashPayment> getAllCashPaymentsByChildId(UUID childId, LocalDate start, LocalDate end) {
        return repository.findAllByChildIdBetweenDates(childId, start, end);
    }

    @Override
    public List<CashPayment> getAllCashPaymentsByGuardianId(UUID guardianId, LocalDate start, LocalDate end) {
        return repository.findAllByGuardianIdBetweenDates(guardianId, start, end);
    }

    @Override
    public List<CashPayment> getAllCashPaymentsFromPastMonth(LocalDate start, LocalDate end) {
        return repository.findAllCashPaymentsForPastMonth(start, end);
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ElementNotFoundException(CASH_PAYMENT, id);
        }

        CashPayment cashPayment = repository.findById(id).get();

        repository.deleteById(id);

        facade.decreaseBalance(cashPayment.getChildId(),
                cashPayment.getTransactionAmount(),
                "Removed cash payment" + cashPayment.getTitle());
}

    @Override
    public void update(CashPayment cashPayment) {
        if (repository.findById(cashPayment.getId()).isEmpty()) {
            throw new ElementNotFoundException(CASH_PAYMENT, cashPayment.getId());
        }

        BigDecimal balanceToUpdate = BigDecimal.ZERO;
        CashPayment cashPaymentToUpdate = repository.findById(cashPayment.getId()).get();
        if (cashPayment.getTransactionAmount() != null) {
            balanceToUpdate = cashPayment.getTransactionAmount()
                    .subtract(cashPaymentToUpdate.getTransactionAmount());
        }
        updateCashPaymentFields(cashPaymentToUpdate, cashPayment);

        repository.save(cashPaymentToUpdate);
        applyBalanceCorrections(balanceToUpdate, cashPayment);
    }

    @Override
    public void save(CashPayment cashPayment) {
        if (isPositive(cashPayment.getTransactionAmount())) {
            repository.save(cashPayment);
            facade.increaseBalance(cashPayment.getChildId(),
                    cashPayment.getTransactionAmount(),
                    cashPayment.getTitle());
        }
    }

    private void updateCashPaymentFields(CashPayment cashPaymentToUpdate, CashPayment cashPayment) {
        if (cashPayment.getTransactionDate() != null) {
            cashPaymentToUpdate.setTransactionDate(cashPayment.getTransactionDate());
        }
        if (cashPayment.getContractorDetails() != null) {
            cashPaymentToUpdate.setContractorDetails(cashPayment.getContractorDetails());
        }
        if (cashPayment.getTitle() != null) {
            cashPaymentToUpdate.setTitle(cashPayment.getTitle());
        }
        if (cashPayment.getTransactionAmount() != null) {
            cashPaymentToUpdate.setTransactionAmount(cashPayment.getTransactionAmount());
        }
        if (cashPayment.getTransactionCurrency() != null) {
            cashPaymentToUpdate.setTransactionCurrency(cashPayment.getTransactionCurrency());
        }
        if (cashPayment.getChildId() != null) {
            cashPaymentToUpdate.setChildId(cashPayment.getChildId());
        }
        if (cashPayment.getGuardianId() != null) {
            cashPaymentToUpdate.setGuardianId(cashPayment.getGuardianId());
        }
    }

    private void applyBalanceCorrections(BigDecimal balanceToUpdate, CashPayment cashPayment) {
        if (!balanceToUpdate.equals(BigDecimal.ZERO)) {
            facade.applyBalanceCorrection(cashPayment.getChildId(),
                    cashPayment.getTransactionAmount(),
                    cashPayment.getTitle());
        }
    }
}
