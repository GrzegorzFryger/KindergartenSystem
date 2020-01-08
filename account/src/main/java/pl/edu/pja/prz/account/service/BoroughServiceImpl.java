package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.repository.BoroughRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

@Service
public class BoroughServiceImpl implements BoroughService {
    private static final String BOROUGH = "Borough";

    private final BoroughRepository boroughRepository;

    @Autowired
    public BoroughServiceImpl(BoroughRepository boroughRepository) {
        this.boroughRepository = boroughRepository;
    }


    @Override
    public Borough createBorough(Borough borough) {
        return boroughRepository.save(borough);
    }

    @Override
    public void addChildToBorough(Child child, Borough borough) {
        borough.addChild(child);
        boroughRepository.save(borough);
    }

    @Override
    public Borough findBorough(Long id) {
        return boroughRepository.findById(id).orElseThrow(
                () -> {
                    throw new ElementNotFoundException(BOROUGH, id);
                }
        );
    }

    @Override
    public Borough updateBorough(Borough borough) {
        Borough boroughToUpdate = boroughRepository.findById(borough.getId()).orElseThrow(
                () -> {
                    throw new ElementNotFoundException(BOROUGH, borough.getId());
                }
        );
        if (borough.getName() != null) {
            boroughToUpdate.setName(borough.getName());
        }
        if (borough.getAddress() != null) {
            boroughToUpdate.setAddress(borough.getAddress());
        }
        if (borough.getPhone() != null) {
            boroughToUpdate.setPhone(borough.getPhone());
        }
        if (borough.getEmail() != null) {
            boroughToUpdate.setEmail(borough.getEmail());
        }
        if (borough.getNipNumber() != null) {
            boroughToUpdate.setNipNumber(borough.getNipNumber());
        }
        return boroughRepository.save(boroughToUpdate);
    }

}
