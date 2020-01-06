package pl.edu.pja.prz.account.facade.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.value.Address;
import pl.edu.pja.prz.account.model.value.Phone;

@Component
public class BoroughMapperImpl implements BoroughMapper {

    @Override
    public Address toAddress(BoroughDto boroughDto) {
        if (boroughDto == null) {
            return null;
        }
        return new Address(boroughDto.getPostalCode(), boroughDto.getCity(), boroughDto.getStreetNumber());
    }

    @Override
    public Phone toPhone(BoroughDto boroughDto) {
        if (boroughDto == null) {
            return null;
        }
        return new Phone(boroughDto.getPhone());
    }

    @Override
    public BoroughDto fromBorough(Borough borough) {
        if (borough == null) {
            return null;
        }
        var boroughDto = new BoroughDto();

        boroughDto.setId(borough.getId());
        boroughDto.setName(borough.getName());

        boroughDto.setCity(borough.getAddress().getCity());
        boroughDto.setPostalCode(borough.getAddress().getPostalCode());
        boroughDto.setStreetNumber(borough.getAddress().getStreetNumber());

        boroughDto.setPhone(borough.getPhone().getPhone());
        boroughDto.setNipNumber(borough.getNipNumber());

        return boroughDto;
    }
}
