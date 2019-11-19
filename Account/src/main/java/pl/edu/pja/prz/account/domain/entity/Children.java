package pl.edu.pja.prz.account.domain.entity;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pja.prz.account.domain.value.*;

import java.util.UUID;


public class Children extends BaseEntity<UUID> {

	private Long peselNumber;
	private Status status;
	@Getter
	@Setter
	private Gender gender;
	@Getter
	@Setter
	private FullName fullName;
	@Getter
	@Setter
	private Age age;
	@Getter
	@Setter
	private Address address;
	@Getter
	@Setter
	private StudyPeriod studyPeriod;
	@Getter
	@Setter
	private Borough borough;

	public Children() {
	}


}
