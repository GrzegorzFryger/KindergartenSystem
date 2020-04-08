package pl.edu.pja.prz.groups.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupModelTest {
	private Group group1;
	private Group group2;

	@BeforeEach
	public void setUp() {
		group1 = new Group();
		group2 = new Group();

		group1.setGroupName("Grupa");
		group2.setGroupName("Grupa");

		group1.setGroupDescription("Opis");
		group2.setGroupDescription("Opis");
	}

	@Test
	public void shouldReturnSameHashCodeWhenObjectsAreEqual() {
		//Given

		//When
		int code1 = group1.hashCode();
		int code2 = group2.hashCode();

		//Then
		assertEquals(code1, code2);
	}

	@Test
	public void shouldReturnDifferentHashCodeWhenObjectsAreDifferent() {
		//Given

		//When
		group1.setId(1L);
		int code1 = group1.hashCode();
		int code2 = group2.hashCode();

		//Then
		assertNotEquals(code1, code2);
	}

	@Test

	public void shouldReturnTrueIfObjectsAreEqual() {
		//Given

		//When
		boolean firstToSecond = group1.equals(group2);
		boolean secondToFirst = group2.equals(group1);

		//Then
		assertTrue(firstToSecond);
		assertTrue(secondToFirst);
	}
}
