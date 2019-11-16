package pl.edu.pja.prz.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.edu.pja.prz.account.AccountConfiguration;

@SpringBootApplication
@Import(AccountConfiguration.class)
//@ComponentScan("pl.edu.pja.prz")
public class KindergartenSystem {
	public static void main(String[] args) {
		SpringApplication.run(KindergartenSystem.class, args);
	}
}
