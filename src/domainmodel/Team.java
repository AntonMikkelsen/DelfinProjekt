package domainmodel;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private AgeCategory ageCategory;
    private ArrayList<Person> juniorTeam = new ArrayList<>();
    private ArrayList<Person> seniorTeam = new ArrayList<>();
    private ArrayList<Person> casualSwimmers = new ArrayList<>();
    private ArrayList<Person> competitiveSwimmers = new ArrayList<>();
    private ArrayList<Person> allSwimmers = new ArrayList<>();


    public Team(String teamName, AgeCategory ageCategory) {
        this.teamName = teamName;
        this.ageCategory = ageCategory;
    }


    public void addToTeam(Person person) {
        if (isJunior(person)) {
            juniorTeam.add(person);
            System.out.println(person + "has been added to the Junior Team");
        } else {
            seniorTeam.add(person);
            System.out.println(person + "has been added to the Senior Team");
        }
    }

    public Boolean isJunior(Person person) {
        return person.calculateAge() < 18;
    }

    public String getTeamName() {
        return teamName;
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
        casualSwimmers.remove(person);
    }



    /*
    public void addToTeam(Person person){
        if(person.calculateAge() < 18){
            juniorTeam.add(person);
        }
    }
     */
}
