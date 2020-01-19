package pl.edu.pja.prz.payments.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuartzJob(name="tuition", description = "responsible for execute tuition payemnts")
public class TuitionJob extends RecurringPaymentJob {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public TuitionJob() {
		super();
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		//todo
	}



}
