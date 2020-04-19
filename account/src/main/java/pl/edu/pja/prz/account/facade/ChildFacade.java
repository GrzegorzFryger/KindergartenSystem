package pl.edu.pja.prz.account.facade;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChildFacade {

    ChildDto createChild(ChildDto childDto);

	ChildDto updateChild(ChildDto childDto);

	ChildDto findChildById(UUID id);

    Optional<ChildDto> findByFullNameOrAddress(String name, String surname, @Nullable String street) throws MoreThanOneElement;

    List<ChildDto> searchByFullName(FullName fullName);

    List<ChildDto> getAllChildren();
}
