package pl.edu.pja.prz.payments.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.scheduler.annotation.QuartzJob;

import java.time.LocalDate;

@QuartzJob(name="tuition", description = "responsible for execute tuition payemnts")
public class TuitionJob extends RecurringPaymentJob {
	private static final String TITLE_TEMPLATE = "Child with id: %s, payment for %s in the amount of %s";
	private final static Logger logger = LoggerFactory.getLogger(TuitionJob.class);

	public TuitionJob() {
		super();
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		this.recurringPaymentRepository.findAllByStatus(Status.ACTIVE)
				.stream()
				.filter(recurringPayment -> {
					var periodValidity = recurringPayment.getPeriodValidity();
					var now = LocalDate.now();
					return !(now.isBefore(periodValidity.getStartDate()) || now.isAfter(periodValidity.getEndDate()));
				})
				.forEach(recurringPayment ->
						this.financesFacade.decreaseBalance(
								recurringPayment.getChild().getChildId(),
								recurringPayment.calculateAmountWithDiscount(),
								createTuitionPaymentTitle(recurringPayment)
						));

		System.out.println("Fire tution ");
	}

	private String createTuitionPaymentTitle(RecurringPayment recurringPayment) {
		return String.format(
				TITLE_TEMPLATE,
				recurringPayment.getChild().getChildId(),
				recurringPayment.getDescription(),
				recurringPayment.calculateAmountWithDiscount()
		);
	}
}
