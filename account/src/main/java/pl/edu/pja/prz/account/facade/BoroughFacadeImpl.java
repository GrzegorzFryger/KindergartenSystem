package pl.edu.pja.prz.account.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.facade.mapper.BoroughMapper;
import pl.edu.pja.prz.account.service.BoroughService;

@Component
public class BoroughFacadeImpl implements BoroughFacade {

    private final BoroughService boroughService;
    private final BoroughMapper boroughMapper;

    public BoroughFacadeImpl(BoroughService boroughService, BoroughMapper boroughMapper) {
        this.boroughService = boroughService;
        this.boroughMapper = boroughMapper;
    }

    @Override
    public BoroughDto createBorough(BoroughDto boroughDto) {
        return boroughMapper.fromBorough(
                boroughService.createBorough(
                        boroughMapper.toBorough(boroughDto)
                )
        );
    }

    @Override
    public BoroughDto findBorough(Long id) {
        return boroughMapper.fromBorough(
                boroughService.findBorough(id)
        );
    }

    @Override
    public BoroughDto updateBorough(BoroughDto boroughDto) {
        return boroughMapper.fromBorough(
                boroughService.updateBorough(
                        boroughMapper.toBorough(boroughDto)
                )
        );
    }
}