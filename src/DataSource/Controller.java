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

    public void removePerson(Person person){
        persons.add(person);
    }

    public void removeTeamCompetetiveSwimmers(Person person){
        removeTeamCompetetiveSwimmers(person);
    }

    public void removeTeamCasualSwimmers(Person person){
        removeTeamCasualSwimmers(person);
    }

    public void addTeamCompetitiveSwimmers(Person person){
        addTeamCompetitiveSwimmers(person);
    }

    public void addTeamCasualSwimmers(Person person){
        addTeamCasualSwimmers(person);
    }

}
