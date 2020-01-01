package pl.edu.pja.prz.account.facade.mapper;

import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.FullName;
import pl.edu.pja.prz.account.model.value.StudyPeriod;

public interface ChildMapper {
	FullName toFullName(ChildDto childDto);
	Address toAddress(ChildDto childDto);
	Age toAge(ChildDto childDto);
	StudyPeriod toStudyPeriod(ChildDto childDto);
	ChildDto fromChild(Child child);
}
