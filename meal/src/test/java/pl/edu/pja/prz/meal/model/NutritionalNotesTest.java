package pl.edu.pja.prz.meal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalNotesTest {
    private NutritionalNotes nutritionalNotes;
    private NutritionalNotes nutritionalNotes2;

    @BeforeEach
    public void setUp() {
        nutritionalNotes = new NutritionalNotes();
        nutritionalNotes2 = new NutritionalNotes();

        nutritionalNotes.setNutritionalNotesValue("Some notes");
        nutritionalNotes2.setNutritionalNotesValue("Some notes");
    }

    @Test
    public void Should_ReturnSameHashCode_When_ObjectsAreEqual() {
        //Given

        //When
        int code1 = nutritionalNotes.hashCode();
        int code2 = nutritionalNotes2.hashCode();

        //Then
        assertEquals(code1, code2);
    }

    @Test
    public void Should_ReturnDifferentHashCode_When_ObjectsAreDifferent() {
        //Given

        //When
        nutritionalNotes.setNutritionalNotesValue("Totally different notes");

        int code1 = nutritionalNotes.hashCode();
        int code2 = nutritionalNotes2.hashCode();

        //Then
        assertNotEquals(code1, code2);
    }

    @Test

    public void Should_ReturnTrue_When_ObjectsAreEqual() {
        //Given

        //When
        boolean firstToSecond = nutritionalNotes.equals(nutritionalNotes2);
        boolean secondToFirst = nutritionalNotes2.equals(nutritionalNotes);

        //Then
        assertTrue(firstToSecond);
        assertTrue(secondToFirst);
    }
}