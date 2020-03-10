package pl.edu.pja.prz.account.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.dto.BoroughDto;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BoroughMapperTest {
    private BoroughMapper boroughMapper;
    private Borough borough;
    private BoroughDto boroughDto;

    @BeforeEach
    public void setUp() {
        this.boroughMapper = Mappers.getMapper( BoroughMapper.class );
        borough = new Borough();
        boroughDto = new BoroughDto();
        Address address = new Address("70-700", "City", "Street 256");
        Phone phone = new Phone("123132123");

        borough.setName("Test Borough");
        borough.setAddress(address);
        borough.setPhone(phone);
        borough.setEmail("test@test.com");
        borough.setNipNumber("99576122623");

        boroughDto.setName("Test Borough");
        boroughDto.setPostalCode(address.getPostalCode());
        boroughDto.setCity(address.getCity());
        boroughDto.setStreetNumber(address.getStreetNumber());
        boroughDto.setPhone(phone.getPhone());
        boroughDto.setEmail("test@test.com");
        boroughDto.setNipNumber("99576122623");
    }

    @Test
    public void Should_MapBorough() {
        //When
        BoroughDto newBoroughDto = boroughMapper.fromBorough(borough);

        //Then
        verifyDto(newBoroughDto);
    }

    private void verifyDto(BoroughDto boroughDto) {
        assertNotNull(boroughDto);
        assertEquals("Test Borough", boroughDto.getName());
        assertEquals("70-700", boroughDto.getPostalCode());
        assertEquals("City", boroughDto.getCity());
        assertEquals("Street 256", boroughDto.getStreetNumber());
        assertEquals("123132123", boroughDto.getPhone());
        assertEquals("test@test.com", boroughDto.getEmail());
        assertEquals("99576122623", boroughDto.getNipNumber());
    }
}
