package pl.edu.pja.prz.account.service.utilities;

import pl.edu.pja.prz.account.model.value.Gender;

import java.time.LocalDate;

public interface PeselService {
	LocalDate extractDateOfBirth(String pesel);
	Gender extractGender(String pesel);
}
