package pl.edu.pja.prz.receivables.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.repository.CashPaymentRepository;
import pl.edu.pja.prz.receivables.util.BigDecimalUtils;

import java.util.List;

@Service
public class CashPaymentServiceImpl implements CashPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(CashPaymentService.class);
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
