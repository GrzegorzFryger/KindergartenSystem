package pl.edu.pja.prz.groups.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class GroupBuilderTest {
	@Test
	public void shouldBuildGroup() {
		//Given
		GroupBuilder builder = new GroupBuilder();
		Set<Child> children = new HashSet<>();

		//When
		Group result = builder
				.withGroupName("Nazwa grupy")
				.withChildren(children)
				.withGroupDescription("Opis grupy")
				.build();

		//Then
		assertNotNull(result);
		assertEquals("Nazwa grupy", result.getGroupName());
		assertEquals("Opis grupy", result.getGroupDescription());
	}
}
