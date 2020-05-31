package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.BoroughFacade;
import pl.edu.pja.prz.account.model.dto.BoroughChildDto;
import pl.edu.pja.prz.account.model.dto.BoroughDto;
import pl.edu.pja.prz.account.model.dto.ChildDto;

import java.util.Set;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
public class BoroughController {
    private final BoroughFacade boroughFacade;

    @Autowired
    public BoroughController(BoroughFacade boroughFacade) {
        this.boroughFacade = boroughFacade;
    }

    @GetMapping("borough/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<BoroughDto> findBorough(@PathVariable Long id) {
        return new ResponseEntity<>(boroughFacade.findBorough(id), HttpStatus.OK);
    }

    @PostMapping("borough")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<BoroughDto> createBorough(@RequestBody BoroughDto boroughDto) {
        return new ResponseEntity<>(boroughFacade.createBorough(boroughDto), HttpStatus.OK);
    }

    @PutMapping("borough")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<BoroughDto> updateBorough(@RequestBody BoroughDto boroughDto) {
        return new ResponseEntity<>(boroughFacade.updateBorough(boroughDto), HttpStatus.OK);
    }

    @GetMapping("borough/{id}/children")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Set<ChildDto>> findAllChildrenFrom(@PathVariable Long id) {
        return new ResponseEntity<>(boroughFacade.findAllChildrenFrom(id), HttpStatus.OK);
    }

    @PutMapping("borough/child")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<BoroughDto> appendChild(@RequestBody BoroughChildDto borough) {
        return new ResponseEntity<>(boroughFacade.appendChild(borough), HttpStatus.OK);
    }

    @DeleteMapping("borough/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<?> deleteBorough(@PathVariable Long id) {
        boroughFacade.deleteBorough(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
