package pl.edu.pja.prz.payments.job;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.finances.facade.FinancesFacade;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

@Component
public abstract class RecurringPaymentJob implements Job {
	protected FinancesFacade financesFacade;
    protected RecurringPaymentRepository recurringPaymentRepository;

	@Autowired
    public void setFinancesFacade(FinancesFacade financesFacade, RecurringPaymentRepository recurringPaymentRepository) {
		this.financesFacade = financesFacade;
        this.recurringPaymentRepository = recurringPaymentRepository;
	}


}
