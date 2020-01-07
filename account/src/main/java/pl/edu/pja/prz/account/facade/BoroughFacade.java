package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.facade.mapper.BoroughMapper;
import pl.edu.pja.prz.account.service.BoroughService;

@Service
public class BoroughFacade {
    private final BoroughMapper boroughMapper;
    private final BoroughService boroughService;

    @Autowired
    public BoroughFacade(BoroughMapper boroughMapper, BoroughService boroughService) {
        this.boroughMapper = boroughMapper;
        this.boroughService = boroughService;
    }

    public BoroughDto createBorough(BoroughDto boroughDto) {
        return boroughMapper.fromBorough(
                boroughService.createBorough(
                        boroughDto.getName(),
                        boroughMapper.toAddress(boroughDto),
                        boroughMapper.toPhone(boroughDto),
                        boroughDto.getEmail(),
                        boroughDto.getNipNumber()
                )
        );
    }

    public BoroughDto findBorough(Long id) {
        return boroughMapper.fromBorough(
                boroughService.findBorough(id)
        );
    }

    public BoroughDto updateBorough(BoroughDto boroughDto) {
        return boroughMapper.fromBorough(
                boroughService.updateBorough(
                        boroughMapper.toBorough(boroughDto)
                )
        );
    }
}
