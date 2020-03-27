package pl.edu.pja.prz.commons.event;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.context.ApplicationEvent;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;

public class AppendChildEvent extends ApplicationEvent {
	private final Logger logger = LoggerFactory.logger(AppendChildEvent.class);
	private GuardianChildDependency guardianChildDependency;

	public AppendChildEvent(Object source, GuardianChildDependency dependency) {
		super(source);
		this.guardianChildDependency = dependency;
		logger.infof("Event AppendChild was created for guardian id - {} and child - {}",
				dependency.getGuardianId().toString(), dependency.getChildId().toString()
		);
	}

	public GuardianChildDependency getGuardianChildDependency() {
		return guardianChildDependency;
	}
}
