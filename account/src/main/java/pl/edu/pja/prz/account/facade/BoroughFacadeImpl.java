package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.facade.mapper.BoroughMapper;
import pl.edu.pja.prz.account.service.BoroughService;

@Service
public class BoroughFacadeImpl implements BoroughFacade {
    private final BoroughService boroughService;
    private final BoroughMapper boroughMapper;

    @Autowired
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

    @Override
    public void deleteBorough(Long id) {
        boroughService.deleteBorough(id);
    }
}