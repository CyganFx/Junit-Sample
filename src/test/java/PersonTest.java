import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PersonTest {

    private AdminPanel adminPanel;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Instantiating Person Manager");
        adminPanel = new AdminPanel();
    }

    @Test
    @DisplayName("Should Create Person")
    public void shouldCreatePerson() {
        adminPanel.addPerson("Duman", "Ishanov", "87772292331");
        assertFalse(adminPanel.getAllPersons().isEmpty());
        assertEquals(1, adminPanel.getAllPersons().size());
    }

    @Test
    @DisplayName("Should Not Create Person When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            adminPanel.addPerson(null, "Ishanov", "87772292331");
        });
    }

    @Test
    @DisplayName("Should Not Create Person When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                adminPanel.addPerson("Duman", null, "87772292331"));
    }

    @Test
    @DisplayName("Should Not Create Person When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                adminPanel.addPerson("Duman", "Ishanov", null));
    }

    @Test
    @DisplayName("Should Create Person")
    @EnabledOnOs(value = OS.LINUX, disabledReason = "Should Run only on LINUX")
    public void shouldCreatePersonOnMAC() {
        adminPanel.addPerson("Duman", "Ishanov", "87772292331");
        assertFalse(adminPanel.getAllPersons().isEmpty());
        assertEquals(1, adminPanel.getAllPersons().size());
    }

    @Test
    @DisplayName("Phone Number should start with 0")
    public void shouldTestPhoneNumberFormat() {
        adminPanel.addPerson("Duman", "Ishanov", "87772292331");
        assertFalse(adminPanel.getAllPersons().isEmpty());
        assertEquals(1, adminPanel.getAllPersons().size());
    }


    @Nested
    class RepeatedTests {
        @DisplayName("Repeat Person Creation Test 5 Times")
        @RepeatedTest(value = 5,
                name = "Repeating Person Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestPersonCreationRepeatedly() {
            adminPanel.addPerson("Duman", "Ishanov", "87772292331");
            assertFalse(adminPanel.getAllPersons().isEmpty());
            assertEquals(1, adminPanel.getAllPersons().size());
        }
    }

    @Nested
    class ParameterizedTests {
        @DisplayName("Phone Number should match the required Format")
        @ParameterizedTest
        @ValueSource(strings = {"87772292331", "87772292341", "87772292342"})
        public void shouldTestPhoneNumberFormatUsingValueSource(String phoneNumber) {
            adminPanel.addPerson("Duman", "Ishanov", phoneNumber);
            assertFalse(adminPanel.getAllPersons().isEmpty());
            assertEquals(1, adminPanel.getAllPersons().size());
        }
    }

    @Test
    @DisplayName("Test Should Be Disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test Should Not be executed");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
}
