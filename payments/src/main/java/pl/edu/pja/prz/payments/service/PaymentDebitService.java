package pl.edu.pja.prz.payments.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.finances.model.enums.OperationType;
import pl.edu.pja.prz.payments.model.PaymentHistory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.StatusHistoryPayment;
import pl.edu.pja.prz.payments.model.enums.StatusPayment;
import pl.edu.pja.prz.payments.repository.PaymentHistoryRepository;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentDebitService {
    private static final String TITLE_TEMPLATE = "Child with id: %s, payment for %s in the amount of %s";
    private final static Logger logger = LoggerFactory.getLogger(PaymentDebitService.class);
    private final FinancesFacade financesFacade;
    private final RecurringPaymentRepository recurringPaymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    public PaymentDebitService(FinancesFacade financesFacade, RecurringPaymentRepository recurringPaymentRepository, PaymentHistoryRepository paymentHistoryRepository) {
        this.financesFacade = financesFacade;
        this.recurringPaymentRepository = recurringPaymentRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    public List<RecurringPayment> chargeTuitionFee() {
        return this.recurringPaymentRepository.findAllByStatusPayment(StatusPayment.ACTIVE)
                .stream()
                .filter(this::filterActivePayments)
                .peek(this::decreaseBalance)
                .peek(recurringPayment -> savePaymentsHistory(recurringPayment, OperationType.DECREASE))
                .collect(Collectors.toList());
    }

    public void applyBalanceCorrectionForPayment(PaymentHistory paymentHistory) {
        //TODO: Here you need to provide difference between original value and new value instead of only new value
        //      This method was intended for updating balances during delete / update method calling
        this.financesFacade.applyLiabilitiesBalanceCorrection(
                paymentHistory.getChildId(),
                paymentHistory.getAmount(),
                paymentHistory.getDescription()
        );

        paymentHistoryRepository.findById(paymentHistory.getId()).ifPresentOrElse(history -> {
            this.financesFacade.applyLiabilitiesBalanceCorrection(
                    paymentHistory.getChildId(),
                    paymentHistory.getAmount(),
                    paymentHistory.getDescription()
            );

            history.setStatus(StatusHistoryPayment.DELETED);

            this.paymentHistoryRepository.save(history);

        }, () -> {
            throw new ElementNotFoundException("Employee", "Not found with id" + paymentHistory.getChildId());
        });

    }

    private boolean filterActivePayments(RecurringPayment recurringPayment) {
        var periodValidity = recurringPayment.getPeriodValidity();
        var now = LocalDate.now();
        return !(now.isBefore(periodValidity.getStartDate()) || now.isAfter(periodValidity.getEndDate()));
    }

    private void decreaseBalance(RecurringPayment recurringPayment) {
        this.financesFacade.decreaseBalance(
                recurringPayment.getChildId(),
                recurringPayment.calculateAmountWithDiscount(),
                createTuitionPaymentTitle(recurringPayment)
        );
    }

    private String createTuitionPaymentTitle(RecurringPayment recurringPayment) {
        return String.format(
                TITLE_TEMPLATE,
                recurringPayment.getChildId(),
                recurringPayment.getDescription(),
                recurringPayment.calculateAmountWithDiscount()
        );
    }

    private void savePaymentsHistory(RecurringPayment recurringPayment, OperationType operationType) {
        var paymentHistory = new PaymentHistory();
        var amount = recurringPayment.calculateAmountWithDiscount();

        paymentHistory.setChildId(recurringPayment.getChildId());
        paymentHistory.setGuardianId(recurringPayment.getGuardianId());
        paymentHistory.setDescription(recurringPayment.getDescription());

        paymentHistory.setAmount(operationType == OperationType.DECREASE ? amount.negate() : amount);

        paymentHistory.setDate(LocalDate.now());
        paymentHistory.setTypeRecurringPayment(recurringPayment.getTypeRecurringPayment());
        paymentHistory.setOperationType(operationType);
        paymentHistory.setStatus(StatusHistoryPayment.ACTIVE);
        paymentHistoryRepository.save(
                paymentHistory
        );
    }


}
