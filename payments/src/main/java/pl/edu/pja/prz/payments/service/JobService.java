package pl.edu.pja.prz.payments.service;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import pl.edu.pja.prz.payments.job.QuartzJob;
import pl.edu.pja.prz.payments.model.JobInfo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Class ANNOTATION_CLASS = QuartzJob.class;
	private String basePackage;

	public JobService(String basePackage) {
		this.basePackage = basePackage;
	}

	public List<JobInfo> getAllExistingJobs() {
		return createComponentScanner()
				.findCandidateComponents(basePackage)
				.stream()
				.map(this::createJobInfoFromAnnotation)
				.collect(Collectors.toList());
	}

	public Optional<JobInfo> getJobInfoByName(String name) {
		return getAllExistingJobs()
				.stream()
				.filter(jobInfo -> jobInfo.getName().equals(name))
				.reduce((u, v) -> {
					throw new IllegalStateException("More than one found");
				});
	}

	@SuppressWarnings("unchecked")
	private JobInfo createJobInfoFromAnnotation(BeanDefinition beanDef) {
		try {
			Class<? extends Job> cl =
					(Class<? extends Job>) Class.forName(beanDef.getBeanClassName());
			QuartzJob job = (QuartzJob) cl.getAnnotation(ANNOTATION_CLASS);

			return new JobInfo(
					job.name(),
					job.description(),
					cl.getName(),
					cl
			);
		} catch (Exception e) {
			logger.warn("Can not assign job class");
			throw new IllegalArgumentException("Business exception");
		}
	}
	@SuppressWarnings("unchecked")
	private ClassPathScanningCandidateComponentProvider createComponentScanner() {
		var provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(ANNOTATION_CLASS));
		return provider;
	}

}
