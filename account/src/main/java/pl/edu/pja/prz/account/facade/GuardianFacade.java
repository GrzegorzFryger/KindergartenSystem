package pl.edu.pja.prz.account.facade;

import org.springframework.lang.Nullable;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GuardianFacade {

    GuardianDto createGuardian(AccountDto accountDto);

    GuardianDto findGuardianById(UUID id);

    List<GuardianDto> findAllGuardians();

    Set<ChildDto> findAllGuardianChildren(UUID id);

    Optional<GuardianDto> findByFullNameOrAddress(String name, String surname, @Nullable String street) throws Exception;
}
