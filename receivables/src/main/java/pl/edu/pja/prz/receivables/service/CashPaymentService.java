package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.CashPayment;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CashPaymentService {
    CashPayment getCashPayment(Long id);

    List<CashPayment> getAllCashPayments();

    List<CashPayment> getAllCashPaymentsByChildId(UUID childId);

    List<CashPayment> getAllCashPaymentsByGuardianId(UUID guardianId);

    List<CashPayment> getAllCashPaymentsByChildId(UUID childId, LocalDate start, LocalDate end);

    List<CashPayment> getAllCashPaymentsByGuardianId(UUID guardianId, LocalDate start, LocalDate end);

    void delete(Long id);

    void update(CashPayment cashPayment);

    void save(CashPayment cashPayment);
}
