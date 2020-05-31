package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
public class ChildController {
    private final ChildFacade childFacade;

    public ChildController(ChildFacade childFacade) {
        this.childFacade = childFacade;
    }

    @GetMapping("child/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<ChildDto> findChildById(@PathVariable UUID id) {
        return new ResponseEntity<>(childFacade.findChildById(id), HttpStatus.OK);
    }

    @GetMapping("children")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<List<ChildDto>> findAllChildren() {
        return new ResponseEntity<>(childFacade.getAllChildren(), HttpStatus.OK);
    }

    @GetMapping("children/search")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<List<ChildDto>> searchByFullName(@RequestParam String name, @RequestParam String surname) {
        return new ResponseEntity<>(childFacade.searchByFullName(new FullName(name, surname)), HttpStatus.OK);
    }

    @GetMapping("children/count")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Long> countChildren() {
        return new ResponseEntity<>(childFacade.countChildren(), HttpStatus.OK);
    }

    @PutMapping("child")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<ChildDto> updateChild(@RequestBody ChildDto childDto) {
        return new ResponseEntity<>(childFacade.updateChild(childDto), HttpStatus.OK);
    }

    @PostMapping("child")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<ChildDto> createChild(@RequestBody ChildDto childDto) {
        return new ResponseEntity<>(childFacade.createChild(childDto), HttpStatus.OK);
    }
    


}
