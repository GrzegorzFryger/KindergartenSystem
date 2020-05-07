package pl.edu.pja.prz.payments.job;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.service.PaymentDebitService;

@Component
public abstract class RecurringPaymentJob implements Job {
    protected PaymentDebitService paymentDebitService;

	@Autowired
    public void setFinancesFacade(PaymentDebitService paymentDebitService) {
        this.paymentDebitService = paymentDebitService;
	}

}
