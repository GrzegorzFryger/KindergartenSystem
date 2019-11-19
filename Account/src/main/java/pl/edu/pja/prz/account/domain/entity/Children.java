package pl.edu.pja.prz.account.domain.entity;

import pl.edu.pja.prz.account.domain.value.Address;
import pl.edu.pja.prz.account.domain.value.FullName;

import java.util.UUID;

public class Children extends BaseEntity<UUID> {

	private FullName fullName;
	private Address address;
	private Long peselNumber;
	private StudyPeriod studyPeriod;
	private Borough borough;
	private Status status;

	public Children() {
	}


}
