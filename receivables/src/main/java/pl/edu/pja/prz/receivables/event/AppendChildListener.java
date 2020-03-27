package pl.edu.pja.prz.receivables.event;

import org.springframework.context.ApplicationListener;
import pl.edu.pja.prz.commons.event.AppendChildEvent;

public interface AppendChildListener extends ApplicationListener<AppendChildEvent> {
	@Override
	void onApplicationEvent(AppendChildEvent appendChildEvent);
}
