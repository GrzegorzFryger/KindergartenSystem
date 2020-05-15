package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import java.util.UUID;

public class PaymentFactory {
    private static final Status STATUS = Status.NONACTIVE;

    private PaymentFactory() {
    }

    public static RecurringPayment createTuitionPayment(UUID childId, UUID guardianId, Payment payment, PeriodValidity periodValidity) {
        var tuition = new RecurringPayment();
        tuition.setChildId(childId);
        tuition.setGuardianId(guardianId);
        tuition.setDescription(payment.getDescription());
        tuition.setBaseAmount(payment.getBaseAmount());
        tuition.setPeriodValidity(periodValidity);
        tuition.setTypeRecurringPayment(TypeRecurringPayment.TUITION);
        tuition.setStatus(STATUS);
        return tuition;
    }

    public static RecurringPayment createOtherRecurringPayment(UUID childId, UUID guardianId, Payment payment, PeriodValidity periodValidity) {
        var tuition = new RecurringPayment();
        tuition.setChildId(childId);
        tuition.setGuardianId(guardianId);
        tuition.setDescription(payment.getDescription());
        tuition.setBaseAmount(payment.getBaseAmount());
        tuition.setPeriodValidity(periodValidity);
        tuition.setTypeRecurringPayment(TypeRecurringPayment.OTHER);
        tuition.setStatus(STATUS);
        return tuition;
    }


}
