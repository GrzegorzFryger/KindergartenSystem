package pl.edu.pja.prz.account.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.mapper.BoroughMapper;
import pl.edu.pja.prz.account.mapper.BoroughMapperImpl;
import pl.edu.pja.prz.account.mapper.ChildMapper;
import pl.edu.pja.prz.account.mapper.ChildMapperImpl;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.dto.BoroughChildDto;
import pl.edu.pja.prz.account.model.dto.BoroughDto;
import pl.edu.pja.prz.account.service.BoroughService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoroughFacadeImplTest {

    @Mock
    private BoroughService boroughService;
    private BoroughMapper boroughMapper;
    private ChildMapper childMapper;
    private BoroughFacade boroughFacade;

    @BeforeEach
    public void setUp() {
        boroughMapper = new BoroughMapperImpl();
        childMapper = new ChildMapperImpl();

        boroughFacade = new BoroughFacadeImpl(boroughService, boroughMapper, childMapper);
    }

    @Test
    public void Should_CreateBorough() {
        //Given
        BoroughDto boroughDto = new BoroughDto();

        //When
        boroughFacade.createBorough(boroughDto);

        //Then
        verify(boroughService, only()).createBorough(any(Borough.class));
    }

    @Test
    public void Should_FindBorough() {
        //Given

        //When
        boroughFacade.findBorough(1L);

        //Then
        verify(boroughService, only()).findBorough(anyLong());
    }

    @Test
    public void Should_UpdateBorough() {
        //Given
        BoroughDto boroughDto = new BoroughDto();

        //When
        boroughFacade.updateBorough(boroughDto);

        //Then
        verify(boroughService, only()).updateBorough(any(Borough.class));
    }

    @Test
    public void Should_AppendChild() {
        //Given
        BoroughChildDto boroughChildDto = new BoroughChildDto();
        boroughChildDto.setBoroughId(1L);
        boroughChildDto.setChildId(UUID.randomUUID());

        //When
        boroughFacade.appendChild(boroughChildDto);

        //Then
        verify(boroughService, only()).addChildToBorough(anyLong(), any(UUID.class));
    }

    @Test
    public void Should_FindAllChildrenFrom() {
        //Given

        //When
        boroughFacade.findAllChildrenFrom(1L);

        //Then
        verify(boroughService, only()).findAllChildrenFrom(anyLong());
    }

    @Test
    public void Should_DeleteBorough() {
        //Given

        //When
        boroughFacade.deleteBorough(1L);

        //Then
        verify(boroughService, only()).deleteBorough(anyLong());
    }
}
