package pl.edu.pja.prz.account.facade;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.account.model.dto.GuardianChildAssociationDto;
import pl.edu.pja.prz.account.model.dto.GuardianDto;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GuardianFacade {

    GuardianDto createGuardian(AccountDto accountDto);

    GuardianDto findGuardianById(UUID id);

    List<GuardianDto> findAllGuardians();

    Set<ChildDto> findAllGuardianChildren(UUID id);

    List<GuardianDto> appendGuardianToChild(GuardianChildAssociationDto associationDto);

    Optional<GuardianDto> findByFullNameOrAddress(String name, String surname, @Nullable String street) throws Exception;

    List<GuardianDto> searchByFullName(FullName fullName);

    Long countGuardian();

    List<GuardianDto> findByChildId(UUID childId);

    GuardianDto updateGuardian(GuardianDto guardianDto);
}
