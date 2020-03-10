package pl.edu.pja.prz.payments.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.scheduler.annotation.QuartzJob;

@QuartzJob(name="tuition", description = "responsible for execute tuition payemnts")
public class TuitionJob extends RecurringPaymentJob {
	private final static Logger logger = LoggerFactory.getLogger(TuitionJob.class);

	public TuitionJob() {
		super();
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		//todo
	}



}
