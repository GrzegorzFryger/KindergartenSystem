package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;

public interface BoroughService {
    Borough createBorough(Borough borough);

    void addChildToBorough(Child child, Borough borough);

    Borough findBorough(Long id);

    Borough updateBorough(Borough borough);

    void deleteBorough(Long id);
}
