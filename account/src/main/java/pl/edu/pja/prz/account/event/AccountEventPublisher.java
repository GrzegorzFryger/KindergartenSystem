package pl.edu.pja.prz.account.event;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.commons.event.AppendChildEvent;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;

@Component
public class AccountEventPublisher {
	private final Logger logger = LoggerFactory.logger(AccountEventPublisher.class);
	private final ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public AccountEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void appendChildToGuardianEvent(GuardianChildDependency dependency) {
		logger.infof("Created appendChildToGuardianEvent for guardian id - {}, and child id - {}",
				dependency.getGuardianId().toString(), dependency.getChildId().toString()
		);

		applicationEventPublisher.publishEvent(new AppendChildEvent(this, dependency));
	}
}
