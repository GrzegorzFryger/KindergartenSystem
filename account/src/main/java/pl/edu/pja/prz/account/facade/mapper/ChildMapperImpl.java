package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.StudyPeriod;

@Component
public class ChildMapperImpl implements ChildMapper {
	@Override
	public FullName toFullName(ChildDto childDto) {
		if (childDto == null) {
			return null;
		}

		return new FullName(childDto.getName(),childDto.getSurname());
	}

	@Override
	public Address toAddress(ChildDto childDto) {
		if (childDto == null) {
			return null;
		}

		return new Address(childDto.getPostalCode(),childDto.getCity(),childDto.getStreetNumber());
	}

	@Override
	public Age toAge(ChildDto childDto) {
		if (childDto == null) {
			return null;
		}

		return new Age(childDto.getDateOfBirth());
	}

	@Override
	public StudyPeriod toStudyPeriod(ChildDto childDto) {
		if (childDto == null) {
			return null;
		}

		return new StudyPeriod(childDto.getStartDate(),childDto.getEndDate());
	}

	@Override
	public ChildDto fromChild(Child child) {
		if (child == null) {
			return null;
		}

		var childDto = new ChildDto();

		childDto.setId(child.getId());
		childDto.setBoroughId(child.getBorough().getId());

		childDto.setName(child.getFullName().getName());
		childDto.setSurname(child.getFullName().getSurname());

		childDto.setPostalCode(child.getAddress().getPostalCode());
		childDto.setCity(child.getAddress().getCity());
		childDto.setStreetNumber(child.getAddress().getStreetNumber());


		childDto.setDateOfBirth(child.getAge().getDateOfBirth());
		childDto.setPesel(child.getPeselNumber());
		childDto.setGender(child.getGender());

		return childDto;
	}
}
