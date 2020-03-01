package pl.edu.pja.prz.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.repository.BoroughRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.Set;
import java.util.UUID;

@Service
public class BoroughService {
	private static final String BOROUGH = "Borough";
	private final BoroughRepository boroughRepository;

	private final ChildServiceImpl childService;

	@Autowired
	public BoroughService(BoroughRepository boroughRepository, ChildServiceImpl childService) {
		this.boroughRepository = boroughRepository;
		this.childService = childService;
	}

	public Borough addChildToBorough(Long boroughId, UUID childId) {
		return boroughRepository.findById(boroughId).map(borough -> {
					var child = childService.getById(childId);
					borough.addChild(child);
					return boroughRepository.save(borough);
				}
		).orElseThrow(() -> new ElementNotFoundException(BOROUGH, boroughId));
	}


	public Borough createBorough(Borough borough) {
		return boroughRepository.save(borough);
	}

	public void deleteBorough(Long id) {
		Borough boroughToDelete = boroughRepository.findById(id).orElseThrow(
				() -> {
					throw new ElementNotFoundException(BOROUGH, id);
				}
		);
		boroughRepository.delete(boroughToDelete);
	}

	public Borough findBorough(Long id) {
		return boroughRepository.findById(id).orElseThrow(
				() -> {
					throw new ElementNotFoundException(BOROUGH, id);
				}
		);
	}

	public Set<Child> findAllChildrenFrom(Long boroughId) {
		return boroughRepository.findById(boroughId).map(Borough::getChildren)
				.orElseThrow(() -> new ElementNotFoundException(BOROUGH, boroughId));
	}


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
