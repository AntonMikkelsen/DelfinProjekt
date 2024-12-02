package datasource;

import domainmodel.CompetitiveSwimmer;
import domainmodel.Member;
import domainmodel.Person;
import domainmodel.Team;

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

    public static class MembershipRegistrationService {
        private final Controller controller;

        public MembershipRegistrationService(Controller controller) {
            this.controller = controller;
        }

        public List<Person> getAllMembers() {
            return controller.getAllPersons();
        }

        public void addMember(Member member) {
            controller.addPerson(member); // Sørg for, at Controller har denne metode
        }

        public void removeMembers(Member members){
            controller.removePerson(members);
        }

        public List<CompetitiveSwimmer> getTeamMembers(Team team) {
            List<Person> allPersons = controller.getAllPersons();
            List<CompetitiveSwimmer> teamMembers = new ArrayList<>();

            for (Person person : allPersons) {
                if (person instanceof CompetitiveSwimmer) {
                    CompetitiveSwimmer swimmer = (CompetitiveSwimmer) person;
                    if (swimmer.getTeam() == team) {
                        teamMembers.add(swimmer);
                    }
                }

            }
            return teamMembers;
        }
    }

}
