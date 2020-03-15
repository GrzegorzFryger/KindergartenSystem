package pl.edu.pja.prz.core.controller.groups;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_TEACHER;
import static pl.edu.pja.prz.commons.constants.Roles.OR;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_GROUPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.groups.facade.GroupFacade;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(API_GROUPS)
public class GroupsController {
	private final GroupFacade groupFacade;

	@Autowired
	public GroupsController(GroupFacade groupFacade) {
		this.groupFacade = groupFacade;
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@GetMapping("{id}")
	public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
		return new ResponseEntity<>(groupFacade.getGroup(id), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@GetMapping("groups")
	public ResponseEntity<List<GroupDto>> getAllGroups() {
		return new ResponseEntity<>(groupFacade.getAllGroups(), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PostMapping("")
	public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
		return new ResponseEntity<>(groupFacade.createGroup(groupDto), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PutMapping("")
	public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
		return new ResponseEntity<>(groupFacade.updateGroup(groupDto), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		groupFacade.deleteGroup(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PutMapping("add/{groupId}/{childId}")
	public ResponseEntity<GroupDto> addChildToGroup(@PathVariable Long groupId,
													@PathVariable UUID childId) {
		return new ResponseEntity<>(groupFacade.addChildToGroup(groupId, childId), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@GetMapping("list/{id}")
	public ResponseEntity<Set<ChildDto>> findAllChildrenInGroup(@PathVariable Long id) {
		return new ResponseEntity<>(groupFacade.findAllChildrenInGroup(id), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PutMapping("remove/{groupId}/{childId}")
	public ResponseEntity<?> removeChildFromGroup(@PathVariable Long groupId,
													@PathVariable UUID childId) {
		groupFacade.removeChildFromGroup(groupId, childId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
