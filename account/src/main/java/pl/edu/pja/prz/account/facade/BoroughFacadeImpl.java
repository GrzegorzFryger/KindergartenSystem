package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.BoroughChildDto;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.mapper.BoroughMapper;
import pl.edu.pja.prz.account.facade.mapper.ChildMapper;
import pl.edu.pja.prz.account.service.BoroughService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BoroughFacadeImpl implements BoroughFacade {
	private final BoroughService boroughService;
	private final BoroughMapper boroughMapper;
	private final ChildMapper childMapper;
	@Autowired
	public BoroughFacadeImpl(BoroughService boroughService, BoroughMapper boroughMapper, ChildMapper childMapper) {
		this.boroughService = boroughService;
		this.boroughMapper = boroughMapper;
		this.childMapper = childMapper;
	}

	@Override
	public BoroughDto createBorough(BoroughDto boroughDto) {
		return boroughMapper.fromBorough(
				boroughService.createBorough(
						boroughMapper.toBorough(boroughDto)
				)
		);
	}

	@Override
	public BoroughDto findBorough(Long id) {
		return boroughMapper.fromBorough(
				boroughService.findBorough(id)
		);
	}

	@Override
	public BoroughDto updateBorough(BoroughDto boroughDto) {
		return boroughMapper.fromBorough(
				boroughService.updateBorough(
						boroughMapper.toBorough(boroughDto)
				)
		);
	}

	@Override
	public BoroughDto appendChild(BoroughChildDto borough) {
		return boroughMapper.fromBorough(
				boroughService.addChildToBorough(borough.getBoroughId(), borough.getChildId())
		);
	}

	@Override
	public Set<ChildDto> findAllChildrenFrom(Long boroughId) {
		return boroughService.findAllChildrenFrom(boroughId).stream()
				.map(childMapper::fromChild)
				.collect(Collectors.toSet());
	}

	@Override
	public void deleteBorough(Long id) {
		boroughService.deleteBorough(id);
	}
}