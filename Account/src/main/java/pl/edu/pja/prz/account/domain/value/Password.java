package pl.edu.pja.prz.account.domain.value;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Password {

	private LocalDate createDate;
	private String oldPassword;
	private String currentPassword;

}
