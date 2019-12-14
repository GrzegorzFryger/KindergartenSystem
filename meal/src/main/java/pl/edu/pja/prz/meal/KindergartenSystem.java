package pl.edu.pja.prz.meal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(KindergartenSystemConfiguration.class)
//@ComponentScan("pl.edu.pja.prz")
public class KindergartenSystem {
	public static void main(String[] args) {
		SpringApplication.run(KindergartenSystem.class, args);
	}
}
