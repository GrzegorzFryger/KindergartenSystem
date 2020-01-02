package pl.edu.pja.prz.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.edu.pja.prz.account.AccountConfiguration;
import pl.edu.pja.prz.commons.CommonsConfiguration;
import pl.edu.pja.prz.finances.FinancesConfiguration;
import pl.edu.pja.prz.groups.GroupsConfiguration;
import pl.edu.pja.prz.payments.PaymentsConfiguration;
import pl.edu.pja.prz.receivables.ReceivablesConfiguration;

@SpringBootApplication
@Import({AccountConfiguration.class, GroupsConfiguration.class,
		ReceivablesConfiguration.class, PaymentsConfiguration.class,
		FinancesConfiguration.class, CommonsConfiguration.class})
public class KindergartenSystem {
	public static void main(String[] args) {
		SpringApplication.run(KindergartenSystem.class, args);
	}
}
