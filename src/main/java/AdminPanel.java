import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdminPanel {

    Map<String, Person> persons = new ConcurrentHashMap<>();

    public void addPerson(String firstName, String lastName, String phoneNumber) {
        Person person = new Person(firstName, lastName, phoneNumber);
        validatePerson(person);
        checkIfPersonAlreadyExist(person);
        persons.put(generateKey(person), person);
    }

    public Collection<Person> getAllPersons() {
        return persons.values();
    }

    private void checkIfPersonAlreadyExist(Person person) {
        if (persons.containsKey(generateKey(person)))
            throw new RuntimeException("Person Already Exists");
    }

    private void validatePerson(Person person) {
        person.validateFirstName();
        person.validateLastName();
        person.validatePhoneNumber();
    }

    private String generateKey(Person person) {
        return String.format("%s-%s", person.getFirstName(), person.getLastName());
    }
}
