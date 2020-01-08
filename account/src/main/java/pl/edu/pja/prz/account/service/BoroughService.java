package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Phone;

import java.util.Optional;

public interface BoroughService {
    Borough createBorough(Borough borough);

    void addChildToBorough(Child child, Borough borough);

    Borough findBorough(Long id);

    Borough updateBorough(Borough borough);
}
