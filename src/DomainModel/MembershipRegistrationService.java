package DomainModel;

import DataSource.Controller;

import java.util.ArrayList;
import java.util.List;

public class MembershipRegistrationService {
    private final Controller controller;

    public MembershipRegistrationService(Controller controller) {
        this.controller = controller;
    }

    public List<Person> getAllMembers() {
        return controller.getAllPersons();
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
