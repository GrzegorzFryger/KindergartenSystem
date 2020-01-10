package pl.edu.pja.prz.account.facade;

import pl.edu.pja.prz.account.facade.dto.BoroughDto;

public interface BoroughFacade {

    BoroughDto createBorough(BoroughDto boroughDto);

    BoroughDto findBorough(Long id);

    BoroughDto updateBorough(BoroughDto boroughDto);

    void deleteBorough(Long id);
}
