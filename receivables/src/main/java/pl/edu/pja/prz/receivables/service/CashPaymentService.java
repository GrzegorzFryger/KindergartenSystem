package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.CashPayment;

import java.util.List;

public interface CashPaymentService {
    CashPayment getCashPayment(Long id);

    List<CashPayment> getAllCashPayments();

    void delete(Long id);

    void update(CashPayment cashPayment);

    void save(CashPayment cashPayment);
}
