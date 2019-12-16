package pl.edu.pja.prz.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.edu.pja.prz.account.AccountConfiguration;
import pl.edu.pja.prz.payments.PaymentsConfiguration;

@SpringBootApplication
@Import({AccountConfiguration.class, PaymentsConfiguration.class})
public class KindergartenSystem {
	public static void main(String[] args) {
		SpringApplication.run(KindergartenSystem.class, args);
	}
}
