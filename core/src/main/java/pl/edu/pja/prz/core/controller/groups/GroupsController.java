package pl.edu.pja.prz.core.controller.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.groups.facade.GroupFacade;
import pl.edu.pja.prz.groups.facade.dto.GroupDto;

@RestController
@RequestMapping("api/groups/")
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

	@PostMapping("group")
	public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
		return new ResponseEntity<>(groupFacade.createGroup(groupDto), HttpStatus.OK);
	}

	@PutMapping("group")
	public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
		return new ResponseEntity<>(groupFacade.updateGroup(groupDto), HttpStatus.OK);
	}

	@DeleteMapping({"{id}"})
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		groupFacade.deleteGroup(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
