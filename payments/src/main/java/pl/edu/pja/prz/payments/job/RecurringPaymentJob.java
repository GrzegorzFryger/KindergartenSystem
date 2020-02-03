package pl.edu.pja.prz.payments.job;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.finances.facade.FinancesFacade;

@Component
public abstract class RecurringPaymentJob implements Job {
	protected FinancesFacade financesFacade;

	@Autowired
	public void setFinancesFacade(FinancesFacade financesFacade) {
		this.financesFacade = financesFacade;
	}


}
