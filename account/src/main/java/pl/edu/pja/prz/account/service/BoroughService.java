package pl.edu.pja.prz.account.service;

import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;

import java.util.Set;
import java.util.UUID;

public interface BoroughService {
    Borough createBorough(Borough borough);

	Borough addChildToBorough(Long boroughId, UUID childId);

	Borough findBorough(Long id);

	Set<Child> findAllChildrenFrom(Long boroughId);

	Borough updateBorough(Borough borough);

    void deleteBorough(Long id);
}
