package DataSource;

import DomainModel.Person;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Person> persons;

    public Controller() {
        persons = new ArrayList<>();
    }

    public List<Person> getAllPersons(){
        return persons;
    }

    public void addPerson(Person person){
        persons.add(person);
    }
}
