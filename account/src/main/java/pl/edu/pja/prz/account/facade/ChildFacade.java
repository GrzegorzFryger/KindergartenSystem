package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.mapper.ChildMapper;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.account.service.ChildService;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChildFacade {
	private final ChildMapper childMapper;
	private final ChildService childService;

	@Autowired
	public ChildFacade(ChildMapper childMapper, ChildService childService) {
		this.childMapper = childMapper;
		this.childService = childService;
	}

	public ChildDto createChild(ChildDto childDto) {
		if (childDto.getPesel() != null) {
			return childMapper.fromChild(
					childService.createChild(
							childDto.getBoroughId(),
							childMapper.toAddress(childDto),
							childMapper.toFullName(childDto),
							childDto.getPesel(),
							childMapper.toStudyPeriod(childDto)
					)
			);

		} else {
			return childMapper.fromChild(
					childService.createChild(
							childDto.getBoroughId(),
							childMapper.toAddress(childDto),
							childMapper.toAge(childDto),
							childMapper.toFullName(childDto),
							childDto.getGender(),
							childMapper.toStudyPeriod(childDto)
					)
			);
		}

	}

	public ChildDto findChildById(UUID id) {
		return childMapper.fromChild(
				childService.getChildById(id)
		);
	}

	public Optional<ChildDto> findByFullNameOrAddress(String name, String surname, @Nullable String street)
			throws IllegalStateException {
		return childService
				.findByFullNameOrAddressReadOnly(new FullName(name.toLowerCase(), surname.toLowerCase()), street)
				.map(childMapper::fromChild)
				.or(Optional::empty);
	}
}
