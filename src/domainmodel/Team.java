package domainmodel;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private AgeCategory ageCategory;
    private ArrayList<Person> juniorTeam = new ArrayList<>();
    private ArrayList<Person> seniorTeam = new ArrayList<>();
    private ArrayList<Person> juniorTeamCompetitive = new ArrayList<>();
    private ArrayList<Person> seniorTeamCompetitive = new ArrayList<>();
    private ArrayList<Person> casualSwimmers = new ArrayList<>();
    private ArrayList<Person> competitiveSwimmers = new ArrayList<>();
    private ArrayList<Person> allSwimmers = new ArrayList<>();


    public Team(String teamName, AgeCategory ageCategory) {
        this.teamName = teamName;
        this.ageCategory = ageCategory;
    }

    public Boolean isSenior(Person person) {
        return person.calculateAge() >= 18;
    }

    public Boolean isJunior(Person person) {
        return person.calculateAge() < 18;
    }

    public boolean isAgeCategoryValid(Person person) {
        if (ageCategory == AgeCategory.JUNIOR) {
            return isJunior(person);
        } else if (ageCategory == AgeCategory.SENIOR) {
            return isSenior(person);
        }
        return false;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addToTeam(Person person) {
        if (!allSwimmers.contains(person)) {
            allSwimmers.add(person);
            if (isJunior(person)) {
                juniorTeam.add(person);
            } else {
                seniorTeam.add(person);
            }
        } else {
            System.out.println(person.getFirstName() + person.getLastName() + " is already in the team.");
        }
    }

    public void removeFromTeam(Person person) {
        allSwimmers.remove(person);
        juniorTeam.remove(person);
        seniorTeam.remove(person);
        casualSwimmers.remove(person);
        competitiveSwimmers.remove(person);
    }


    public void addtoTeamCasualSwimmers(Person person){
        casualSwimmers.add(person);
    }

    public void addtoTeamCompetitiveSwimmers(Person person){
        competitiveSwimmers.add(person);
    }

    public void removeTeamCasualSwimmers(Person person){
        casualSwimmers.remove(person);
    }

    public void removeTeamCompetetiveSwimmers(Person person){
        competitiveSwimmers.remove(person);
    }
}
