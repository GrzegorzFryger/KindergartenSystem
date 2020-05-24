package pl.edu.pja.prz.payments.model;

import pl.edu.pja.prz.payments.model.enums.StatusPayment;
import pl.edu.pja.prz.payments.model.enums.TypeRecurringPayment;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;

import javax.persistence.*;

@Entity
public class RecurringPayment extends Payment implements DiscountCalculator {
    private PeriodValidity periodValidity;
    @ManyToOne
    private Discount discount;
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;
    @Enumerated(EnumType.STRING)
    private TypeRecurringPayment typeRecurringPayment;

    public RecurringPayment() {
        super();
    }


    public PeriodValidity getPeriodValidity() {
        return periodValidity;
    }

    public void setPeriodValidity(PeriodValidity periodValidity) {
        this.periodValidity = periodValidity;
    }

    @Override
    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public TypeRecurringPayment getTypeRecurringPayment() {
        return typeRecurringPayment;
    }

    public void setTypeRecurringPayment(TypeRecurringPayment typeRecurringPayment) {
        this.typeRecurringPayment = typeRecurringPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecurringPayment)) return false;
        if (!super.equals(o)) return false;

        RecurringPayment that = (RecurringPayment) o;

        if (getPeriodValidity() != null ? !getPeriodValidity().equals(that.getPeriodValidity()) : that.getPeriodValidity() != null)
            return false;
        if (getDiscount() != null ? !getDiscount().equals(that.getDiscount()) : that.getDiscount() != null)
            return false;
        if (getStatusPayment() != that.getStatusPayment()) return false;
        return getTypeRecurringPayment() == that.getTypeRecurringPayment();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPeriodValidity() != null ? getPeriodValidity().hashCode() : 0);
        result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        result = 31 * result + (getStatusPayment() != null ? getStatusPayment().hashCode() : 0);
        result = 31 * result + (getTypeRecurringPayment() != null ? getTypeRecurringPayment().hashCode() : 0);
        return result;
    }
}
