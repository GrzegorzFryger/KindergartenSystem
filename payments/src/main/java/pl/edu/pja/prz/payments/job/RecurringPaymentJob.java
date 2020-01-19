package pl.edu.pja.prz.payments.job;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class RecurringPaymentJob implements Job {
	protected FinancesFacade financesFacade;
	protected RecurringPaymentSearchService searchService;

	@Autowired
	public void setFinancesFacade(FinancesFacade financesFacade) {
		this.financesFacade = financesFacade;
	}

	@Autowired
	public void setSearchService(RecurringPaymentSearchService searchService) {
		this.searchService = searchService;
	}

}
