package DomainModel;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private AgeCategory ageCategory;
    private ArrayList<Person> Juniorhold = new ArrayList<>();
    private ArrayList<Person> Seniorhold = new ArrayList<>();

    public Team(String teamName, AgeCategory ageCategory) {
        this.teamName = teamName;
        this.ageCategory = ageCategory;
    }

    public void addToTeam(Person person) {
        if (isJunior(person)) {
            Juniorhold.add(person);
            System.out.println(person + "has been added to the Junior Team");
        } else {
            Seniorhold.add(person);
            System.out.println(person + "has been added to the Senior Team");
        }
    }

    public Boolean isJunior(Person person) {
        return Integer.valueOf(person.getDateOfBirth()) < 18;
    }

    public String getTeamName() {
        return teamName;
    }
}
