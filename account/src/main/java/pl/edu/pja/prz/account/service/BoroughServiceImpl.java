package pl.edu.pja.prz.account.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.repository.BoroughRepository;

import java.util.Optional;

@Service
public class BoroughServiceImpl implements BoroughService {
	private final BoroughRepository boroughRepository;

	public BoroughServiceImpl(BoroughRepository boroughRepository) {
		this.boroughRepository = boroughRepository;
	}
	@Override
	public Borough create(Borough borough) {
		return boroughRepository.save(borough);
	}

	@Override
	public void addChildToBorough(Child child, Borough borough) {
		borough.addChild(child);
		boroughRepository.save(borough);
	}

	@Override
	public Optional<Borough> find(Long id) {
		return boroughRepository.findById(id);
	}

}
