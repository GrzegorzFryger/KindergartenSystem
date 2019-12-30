package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;

import java.util.Optional;

public interface BoroughService {
	Borough create(Borough borough);

	void addChildToBorough(Child child, Borough borough);

	Optional<Borough> find(Long id);
}
