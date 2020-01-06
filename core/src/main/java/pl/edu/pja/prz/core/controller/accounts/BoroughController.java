package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.BoroughFacade;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;

@RestController
@RequestMapping("api/account/")
public class BoroughController {
    private final BoroughFacade boroughFacade;

    @Autowired
    public BoroughController(BoroughFacade boroughFacade) {
        this.boroughFacade = boroughFacade;
    }

    @GetMapping("borough/{id}")
    public ResponseEntity<BoroughDto> findBorough(@PathVariable Long id) {
        return new ResponseEntity<>(boroughFacade.findBorough(id), HttpStatus.OK);
    }

    @PostMapping("borough")
    public ResponseEntity<BoroughDto> createBorough(@RequestBody BoroughDto boroughDto) {
        return new ResponseEntity<>(boroughFacade.createBorough(boroughDto), HttpStatus.OK);
    }

}
