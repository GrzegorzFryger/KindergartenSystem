package pl.edu.pja.prz.receivables.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.repository.CashPaymentRepository;
import pl.edu.pja.prz.receivables.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IncomingPaymentsServiceImpl implements IncomingPaymentsService {
    private final TransactionRepository transactionRepository;
    private final CashPaymentRepository cashPaymentRepository;
    private final DtoMappingService dtoMappingService;

    @Autowired
    public IncomingPaymentsServiceImpl(TransactionRepository transactionRepository,
                                       CashPaymentRepository cashPaymentRepository,
                                       DtoMappingService dtoMappingService) {
        this.transactionRepository = transactionRepository;
        this.cashPaymentRepository = cashPaymentRepository;
        this.dtoMappingService = dtoMappingService;
    }

    @Override
    public List<IncomingPaymentDto> getAllPaymentsForGuardian(UUID guardianId) {
        List<Transaction> transactions = transactionRepository.findAllByGuardianId(guardianId);
        List<CashPayment> cashPayments = cashPaymentRepository.findAllByGuardianId(guardianId);

        return convertToDto(transactions, cashPayments);
    }

    @Override
    public List<IncomingPaymentDto> getAllPaymentsForChild(UUID childId) {
        List<Transaction> transactions = transactionRepository.findAllByChildId(childId);
        List<CashPayment> cashPayments = cashPaymentRepository.findAllByChildId(childId);

        return convertToDto(transactions, cashPayments);
    }

    @Override
    public List<IncomingPaymentDto> getAllPaymentsForGuardian(UUID guardianId, LocalDate from, LocalDate to) {
        //TODO Implement this method
        List<IncomingPaymentDto> incomingPayments = new ArrayList<>();
        return incomingPayments;
    }

    @Override
    public List<IncomingPaymentDto> getAllPaymentsForChild(UUID childId, LocalDate from, LocalDate to) {
        //TODO Implement this method
        List<IncomingPaymentDto> incomingPayments = new ArrayList<>();
        return incomingPayments;
    }

    private List<IncomingPaymentDto> convertToDto(List<Transaction> transactions, List<CashPayment> cashPayments) {
        List<IncomingPaymentDto> incomingPayments = new ArrayList<>();
        transactions.forEach(transaction -> incomingPayments.add(dtoMappingService.fromTransaction(transaction)));
        cashPayments.forEach(cashPayment -> incomingPayments.add(dtoMappingService.fromCashPayment(cashPayment)));
        return incomingPayments;
    }
}
