package pl.edu.pja.prz.receivables.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.repository.CashPaymentRepository;
import pl.edu.pja.prz.receivables.service.CashPaymentService;
import pl.edu.pja.prz.receivables.util.BigDecimalUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CashPaymentServiceImpl implements CashPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(CashPaymentServiceImpl.class);
    private static final String CASH_PAYMENT = "Cash Payment";

    private final CashPaymentRepository repository;

    @Autowired
    public CashPaymentServiceImpl(CashPaymentRepository repository) {
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
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ElementNotFoundException(CASH_PAYMENT, id);
        }
        repository.deleteById(id);
    }

    @Override
    public void update(CashPayment cashPayment) {
        //TODO: fix updating method
        if (repository.findById(cashPayment.getId()).isEmpty()) {
            throw new ElementNotFoundException(CASH_PAYMENT, cashPayment.getId());
        }
        repository.save(cashPayment);
    }

    @Override
    public void save(CashPayment cashPayment) {
        if (BigDecimalUtils.isPositive(cashPayment.getTransactionAmount())) {
            logger.info("Saving cash payment: " + cashPayment.getTitle() + " ["
                    + cashPayment.getTransactionAmount() + " " + cashPayment.getTransactionCurrency() + "]");
            repository.save(cashPayment);
        }
    }
}
