package pl.edu.pja.prz.account.service;

import java.time.LocalDate;

public interface PeselService {
	LocalDate generateDateFromPesel(String pesel);
}
