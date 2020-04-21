package pl.edu.pja.prz.commons.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateUtilsTest {

    @Test
    public void Should_ReturnNull_When_InputIsNull() {
        //Given
        LocalDate input = null;

        //When
        LocalDate dateInPast = DateUtils.getDateOneMonthBehind(input);

        //Then
        assertNull(dateInPast);
    }

    @Test
    public void Should_ReturnCorrectDateInPast() {
        //Given
        List<TestData> testDataList = new ArrayList<>(Arrays.asList(
                build(2020, 1, 1, 2020, 2, 1),
                build(2019, 12, 1, 2020, 1, 1),
                build(2020, 1, 15, 2020, 2, 15),
                build(2020, 2, 29, 2020, 3, 30),
                build(2021, 2, 28, 2021, 3, 30)
        ));

        //When

        //Then
        for (TestData data : testDataList) {
            testMethodResult(data);
        }
    }

    private TestData build(int expectedYear, int expectedMonth, int expectedDay,
                           int inputYear, int inputMonth, int inputDay) {
        LocalDate expectedResult = LocalDate.of(expectedYear, expectedMonth, expectedDay);
        LocalDate input = LocalDate.of(inputYear, inputMonth, inputDay);
        return new TestData(expectedResult, input);
    }

    private void testMethodResult(TestData testData) {
        assertEquals("Expecting one month behind from: " + testData.getInput() + " to be: " + testData.getExpectedResult(),
                testData.getExpectedResult(), DateUtils.getDateOneMonthBehind(testData.getInput()));
    }

    private static class TestData {
        private LocalDate expectedResult;
        private LocalDate input;

        public TestData(LocalDate expected, LocalDate actual) {
            this.expectedResult = expected;
            this.input = actual;
        }

        public LocalDate getExpectedResult() {
            return expectedResult;
        }

        public LocalDate getInput() {
            return input;
        }
    }
}
