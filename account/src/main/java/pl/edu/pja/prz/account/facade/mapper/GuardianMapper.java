package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.model.Guardian;

@Service
public interface GuardianMapper extends AccountMapper {
	GuardianDto fromGuardian(Guardian guardian);
	Guardian toGuardian(GuardianDto guardianDto);
}
