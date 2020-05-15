package pl.edu.pja.prz.payments.job;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import pl.edu.pja.prz.scheduler.annotation.QuartzJob;

@QuartzJob(name = "tuition", description = "responsible for execute tuition payments")
public class TuitionJob extends RecurringPaymentJob {
    private final Logger logger = LoggerFactory.logger(TuitionJob.class);

    public TuitionJob() {
        super();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.paymentDebitService.chargeTuitionFee()
                .forEach(recurringPayment -> {
                    logger.infof("TuitionJob: charge child account with id - {}",
                            recurringPayment.getChildId().toString());
                });
    }
}
