package pl.edu.pja.prz.receivables.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.model.enums.PaymentType;

@Service
public class DtoMappingServiceImpl implements DtoMappingService {
    public IncomingPaymentDto fromCashPayment(CashPayment cashPayment) {
        IncomingPaymentDto dto = new IncomingPaymentDto();
        dto.setPaymentType(PaymentType.CASH);
        dto.setContractorDetails(cashPayment.getContractorDetails());
        dto.setTitle(cashPayment.getTitle());
        dto.setTransactionAmount(cashPayment.getTransactionAmount());
        dto.setTransactionCurrency(cashPayment.getTransactionCurrency());
        dto.setTransactionDate(cashPayment.getTransactionDate());
        dto.setChildId(cashPayment.getChildId());
        dto.setGuardianId(cashPayment.getGuardianId());
        return dto;
    }

    public IncomingPaymentDto fromTransaction(Transaction transaction) {
        IncomingPaymentDto dto = new IncomingPaymentDto();
        dto.setPaymentType(PaymentType.TRANSFER);
        dto.setContractorDetails(transaction.getContractorDetails());
        dto.setTitle(transaction.getTitle());
        dto.setTransactionAmount(transaction.getTransactionAmount());
        dto.setTransactionCurrency(transaction.getTransactionCurrency());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setChildId(transaction.getChildId());
        dto.setGuardianId(transaction.getGuardianId());
        return dto;
    }
}
