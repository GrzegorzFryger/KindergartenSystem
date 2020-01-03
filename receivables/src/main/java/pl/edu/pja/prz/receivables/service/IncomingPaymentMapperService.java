package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;

public interface IncomingPaymentMapperService {
    IncomingPaymentDto fromCashPayment(CashPayment cashPayment);
    IncomingPaymentDto fromTransaction(Transaction transaction);
}
