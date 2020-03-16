package pl.edu.pja.prz.account.facade;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.account.model.dto.GuardianDto;
import pl.edu.pja.prz.account.mapper.ChildMapper;
import pl.edu.pja.prz.account.mapper.GuardianMapper;
import pl.edu.pja.prz.account.service.GuardianService;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GuardianFacadeImpl implements GuardianFacade {
    private final GuardianMapper guardianMapper;
    private final GuardianService guardianService;
    private final ChildMapper childMapper;

    public GuardianFacadeImpl(GuardianMapper guardianMapper, GuardianService guardianService,
                          ChildMapper childMapper) {
        this.guardianMapper = guardianMapper;
        this.guardianService = guardianService;
        this.childMapper = childMapper;
    }

    public GuardianDto createGuardian(AccountDto accountDto) {
        return guardianMapper.fromGuardian(
                guardianService.createGuardianAccount(
                        guardianMapper.toPerson(accountDto),
                        accountDto.getEmail()
                )
        );
    }

    public GuardianDto findGuardianById(UUID id) {
        return guardianMapper.fromGuardian(
                guardianService.getById(id)
        );
    }

    public List<GuardianDto> findAllGuardians() {
        return guardianService.getAll()
                .stream()
                .map(guardianMapper::fromGuardian)
                .collect(Collectors.toList());
    }

    public Set<ChildDto> findAllGuardianChildren(UUID id) {
        return guardianService.getAllChildren(id)
                .stream()
                .map(childMapper::fromChild)
                .collect(Collectors.toSet());
    }

    public Optional<GuardianDto> findByFullNameOrAddress(String name, String surname, @Nullable String street) throws MoreThanOneElement {
        return guardianService
                .findByFullNameOrAddressReadOnly(new FullName(name.toLowerCase(), surname.toLowerCase()), street)
                .map(guardianMapper::fromGuardian)
                .or(Optional::empty);
    }
}