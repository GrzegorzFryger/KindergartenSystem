package pl.edu.pja.prz.core.controller.groups;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_GROUPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.groups.facade.GroupFacade;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(API_GROUPS)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class GroupsController {
	private final GroupFacade groupFacade;

	@Autowired
	public GroupsController(GroupFacade groupFacade) {
		this.groupFacade = groupFacade;
	}

	@GetMapping("{id}")
	public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
		return new ResponseEntity<>(groupFacade.getGroup(id), HttpStatus.OK);
	}

	@GetMapping("groups")
	public ResponseEntity<List<GroupDto>> getAllGroups() {
		return new ResponseEntity<>(groupFacade.getAllGroups(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
		return new ResponseEntity<>(groupFacade.createGroup(groupDto), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
		return new ResponseEntity<>(groupFacade.updateGroup(groupDto), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		groupFacade.deleteGroup(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("add/{groupId}/{childId}")
	public ResponseEntity<GroupDto> addChildToGroup(@PathVariable Long groupId,
													@PathVariable UUID childId) {
		return new ResponseEntity<>(groupFacade.addChildToGroup(groupId, childId), HttpStatus.OK);
	}

	@GetMapping("list/{id}")
	public ResponseEntity<Set<ChildDto>> findAllChildrenInGroup(@PathVariable Long id) {
		return new ResponseEntity<>(groupFacade.findAllChildrenInGroup(id), HttpStatus.OK);
	}

	@PutMapping("remove/{groupId}/{childId}")
	public ResponseEntity<?> removeChildFromGroup(@PathVariable Long groupId,
													@PathVariable UUID childId) {
		groupFacade.removeChildFromGroup(groupId, childId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
