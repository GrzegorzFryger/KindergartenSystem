package pl.edu.pja.prz.account.utilites;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.enums.Gender;

import java.time.LocalDate;

@Service
public interface PeselService {
	LocalDate extractDateOfBirth(String pesel);
	Gender extractGender(String pesel);
}
