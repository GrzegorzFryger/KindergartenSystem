package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.facade.dto.BoroughChildDto;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.facade.dto.ChildDto;

import java.util.Set;

public interface BoroughFacade {

    BoroughDto createBorough(BoroughDto boroughDto);

    BoroughDto findBorough(Long id);

    BoroughDto updateBorough(BoroughDto boroughDto);

	BoroughDto appendChild(BoroughChildDto borough);

	Set<ChildDto> findAllChildrenFrom(Long boroughId);

	void deleteBorough(Long id);
}
