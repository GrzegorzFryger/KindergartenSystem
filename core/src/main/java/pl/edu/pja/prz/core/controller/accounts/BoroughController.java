package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.BoroughFacade;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.service.BoroughService;

@RestController
@RequestMapping("api/account/")
public class BoroughController {
    private final BoroughService boroughService;
    private final BoroughFacade boroughFacade;

    @Autowired
    public BoroughController(BoroughService boroughService, BoroughFacade boroughFacade) {
        this.boroughService = boroughService;
        this.boroughFacade = boroughFacade;
    }

    //TODO: change find method to use facade
    @GetMapping("borough/{id}")
    public ResponseEntity<Borough> findGuardianById(@PathVariable Long id) {
        return new ResponseEntity<>(boroughService.findBorough(id).get(), HttpStatus.OK);
    }

    @PostMapping("borough")
    public ResponseEntity<BoroughDto> createGuardian(@RequestBody BoroughDto boroughDto) {
        return new ResponseEntity<>(boroughFacade.createBorough(boroughDto), HttpStatus.OK);
    }

}
