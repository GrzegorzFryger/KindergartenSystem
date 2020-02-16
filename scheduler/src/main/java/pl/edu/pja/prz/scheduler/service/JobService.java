package pl.edu.pja.prz.scheduler.service;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import pl.edu.pja.prz.scheduler.annotation.QuartzJob;
import pl.edu.pja.prz.scheduler.model.JobInfo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobService {
	private static final Logger logger = LoggerFactory.getLogger(JobService.class);
	private static final Class ANNOTATION_CLASS = QuartzJob.class;
	private String basePackage;

	public JobService(String basePackage) {
		this.basePackage = basePackage;
	}

	public List<JobInfo> getAllExistingJobs() {
		return createComponentScanner()
				.findCandidateComponents(basePackage)
				.stream()
				.map(this::createJobInfoFromAnnotation)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

	public Optional<JobInfo> getJobInfoByName(String name) {
		return getAllExistingJobs()
				.stream()
				.filter(jobInfo -> jobInfo.getName().equals(name))
				.reduce((u, v) -> {
					logger.error("Failed job service: find more than one element with name - {}", name);
					throw new IllegalArgumentException("Job service: find more than one element with name = " + name);
				});
	}

	@SuppressWarnings("unchecked")
	private Optional<JobInfo> createJobInfoFromAnnotation(BeanDefinition beanDef) {
		try {
			Class<? extends Job> cl = (Class<? extends Job>) Class.forName(beanDef.getBeanClassName());
			QuartzJob job = (QuartzJob) cl.getAnnotation(ANNOTATION_CLASS);

			return Optional.of(new JobInfo(job.name(), job.description(), cl.getName(), cl));
		} catch (Exception e) {
			logger.error("Failed job service: can not create JobInfo from - {}", beanDef.getBeanClassName());
			return Optional.empty();
		}
	}

	@SuppressWarnings("unchecked")
	private ClassPathScanningCandidateComponentProvider createComponentScanner() {
		var provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(ANNOTATION_CLASS));
		return provider;
	}

}
