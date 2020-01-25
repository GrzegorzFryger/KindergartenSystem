package pl.edu.pja.prz.mail.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailUtilsTest {

    private List<TestObject> testObjects;

    /**
     * Source of test cases:
     * https://gist.github.com/cjaoude/fd9910626629b53c4d25
     */
    @BeforeEach
    public void setUp() {
        testObjects = new ArrayList<>();

        initializeTestList(
                //Positive test cases
                new TestObject("email@domain.com", true),
                new TestObject("firstname.lastname@domain.com", true),
                new TestObject("email@subdomain.domain.com", true),
                new TestObject("firstname+lastname@example.com", true),
                new TestObject("email@[123.123.123.123]", true),
                new TestObject("\"email\"@example.com", true),
                new TestObject("1234567890@example.com", true),
                new TestObject("_______@example.com", true),
                new TestObject("email@example.name", true),
                new TestObject("email@example.museum", true),
                new TestObject("email@example.co.jp", true),
                new TestObject("firstname-lastname@example.com", true),

                //Negative test cases
                new TestObject("plainaddress", false),
                new TestObject("#@%^%#$@#$@#.com", false),
                new TestObject("@example.com", false),
                new TestObject("Joe Smith <email@example.com>", false),
                new TestObject("email.example.com", false),
                new TestObject("email@example@example.com", false),
                new TestObject(".email@example.com", false),
                new TestObject("email.@example.com", false),
                new TestObject("email..email@example.com", false),
                new TestObject("email@example.com (Joe Smith)", false),
                new TestObject("email@example", false),
                new TestObject("email@-example.com", false),
                new TestObject("email@example.web", false),
                new TestObject("email@111.222.333.44444", false),
                new TestObject("email@example..com", false),
                new TestObject("Abc..123@example.com", false)
        );
    }

    @Test
    public void Should_Validate_Emails() {
        //Given

        //When

        //Then
        for (TestObject t : testObjects) {
            verify(t);
        }
    }

    private void initializeTestList(TestObject... objects) {
        testObjects.addAll(Arrays.asList(objects));
    }

    private void verify(TestObject testObject) {
        boolean result = EmailUtils.validateEmail(testObject.email);
        assertEquals(testObject.result, result, "Validation failed for email: " + testObject.email);
    }

    class TestObject {
        private String email;
        private boolean result;

        public TestObject(String email, boolean result) {
            this.email = email;
            this.result = result;
        }
    }
}