package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.BoroughFacade;
import pl.edu.pja.prz.account.facade.dto.BoroughChildDto;
import pl.edu.pja.prz.account.facade.dto.BoroughDto;
import pl.edu.pja.prz.account.facade.dto.ChildDto;

import java.util.Set;

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

    @PutMapping("borough")
    public ResponseEntity<BoroughDto> updateBorough(@RequestBody BoroughDto boroughDto) {
        return new ResponseEntity<>(boroughFacade.updateBorough(boroughDto), HttpStatus.OK);
    }

    @GetMapping("borough/{id}/children")
    public ResponseEntity<Set<ChildDto>> findAllChildrenFrom(@PathVariable Long id) {
        return new ResponseEntity<>(boroughFacade.findAllChildrenFrom(id), HttpStatus.OK);
    }

    @PutMapping("borough/child")
    public ResponseEntity<BoroughDto> appendChild(@RequestBody BoroughChildDto borough) {
        return new ResponseEntity<>(boroughFacade.appendChild(borough), HttpStatus.OK);
    }

    @DeleteMapping("borough/{id}")
    public ResponseEntity<?> deleteBorough(@PathVariable Long id) {
        boroughFacade.deleteBorough(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
