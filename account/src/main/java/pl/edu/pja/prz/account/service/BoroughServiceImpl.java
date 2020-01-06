package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Phone;
import pl.edu.pja.prz.account.repository.BoroughRepository;

@Service
public class BoroughServiceImpl implements BoroughService {
    private final BoroughRepository boroughRepository;

    public BoroughServiceImpl(BoroughRepository boroughRepository) {
        this.boroughRepository = boroughRepository;
    }


    @Override
    public Borough createBorough(String name, Address address, Phone phone, String email, String nipNumber) {
        var borough = new Borough(name, address, phone, email, nipNumber);
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
                    throw new IllegalArgumentException("Borough with id " + id + " not found.");
                }
        );
    }

    @Override
    public Borough updateBorough(Borough borough, Long boroughToUpdateId) {
        Borough boroughToUpdate = boroughRepository.findById(boroughToUpdateId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("Borough with id " + boroughToUpdateId + " not found.");
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
