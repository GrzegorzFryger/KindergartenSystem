package pl.edu.pja.prz.account.facade.mapper;

import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Phone;

public interface BoroughMapper {
    Address toAddress(BoroughDto boroughDto);

    Phone toPhone(BoroughDto boroughDto);

    BoroughDto fromBorough(Borough borough);

    Borough toBorough(BoroughDto boroughDto);
}
