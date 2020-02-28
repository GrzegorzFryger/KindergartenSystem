package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.mapper.ChildMapper;
import pl.edu.pja.prz.account.service.ChildServiceImpl;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChildFacadeImpl implements ChildFacade {
	private final ChildMapper childMapper;
	private final ChildServiceImpl childService;

	@Autowired
	public ChildFacadeImpl(ChildMapper childMapper, ChildServiceImpl childService) {
		this.childMapper = childMapper;
		this.childService = childService;
	}

	public ChildDto createChild(ChildDto childDto) {
		if (childDto.getPesel() != null) {
			return childMapper.fromChild(
					childService.createChild(
							childMapper.toAddress(childDto),
							childMapper.toFullName(childDto),
							childDto.getPesel(),
							childMapper.toStudyPeriod(childDto)
					)
			);

		} else {
			return childMapper.fromChild(
					childService.createChild(
							childMapper.toAddress(childDto),
							childMapper.toAge(childDto),
							childMapper.toFullName(childDto),
							childDto.getGender(),
							childMapper.toStudyPeriod(childDto)
					)
			);
		}

	}

	@Override
	public ChildDto updateChild(ChildDto childDto) {
		return childMapper.fromChild(
				childService.update(

						childMapper.toChild(childDto)
				)
		);
	}

	public ChildDto findChildById(UUID id) {
		return childMapper.fromChild(
				childService.getById(id)
		);
	}

	public Optional<ChildDto> findByFullNameOrAddress(String name, String surname, @Nullable String street) {
		return childService
				.findByFullNameOrAddressReadOnly(new FullName(name.toLowerCase(), surname.toLowerCase()), street)
				.map(childMapper::fromChild)
				.or(Optional::empty);
	}
}
