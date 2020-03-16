package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.enums.Gender;

import java.time.LocalDate;


public interface PeselService {
	LocalDate extractDateOfBirth(String pesel);

	Gender extractGender(String pesel);
}
