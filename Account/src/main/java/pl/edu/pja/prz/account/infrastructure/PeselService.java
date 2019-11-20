package pl.edu.pja.prz.account.infrastructure;

import java.time.LocalDate;

public interface PeselService {
	LocalDate generateDateFromPesel(String pesel);
}
