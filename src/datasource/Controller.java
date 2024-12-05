package datasource;

import domainmodel.CompetitiveSwimmer;
import domainmodel.Member;
import domainmodel.Person;
import domainmodel.Team;
import ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Person> persons;
    private FileHandler fileHandler;
    private ArrayList<Team> teams;
    private Team team;

    public Controller() {
        this.persons = new ArrayList<>();
        this.fileHandler = new FileHandler();
        this.teams = new ArrayList<Team>();
    }

    public void saveMembers(String fileName) {
        ArrayList<Member> members = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Member) {
                members.add((Member) person);
            }
        }
        fileHandler.saveMembersToFile(members, fileName);
    }

    public void loadMembers(String fileName) {
        ArrayList<Member> loadedMembers = fileHandler.loadMembersFromFile(fileName);
        persons.addAll(loadedMembers);
    }

    public List<Person> getAllPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

        public List<Person> getAllMembers() {
            return getAllPersons();
        }

        public void addMemberToTeam(Member member) {
             // Sørg for, at Controller har denne metode
            team.addSwimmersToTeam(member);
        }

        public void displayAllMembers(){
        team.displayAllMembers();
        }

        public void addTeam(Team team) {
            teams.add(team);
        }

        public void removeTeam(Team team) {
            teams.remove(team);
        }


        public void removeMembers(Member members) {
            removePerson(members);
        }



        public List<CompetitiveSwimmer> getTeamMembers(Team team) {
            List<Person> allPersons = getAllPersons();
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


    public List<Person> getAllMembers1() {
        return getAllPersons();
    }


    private void printHeaderLine() {
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-15s%n",
                "ID", "First Name", "Last Name", "Age", "Status", "Team");
        System.out.println("=".repeat(70));
    }

  /*  public void displayAllMembers() {
        List<Person> members = getAllMembers1();

        System.out.println("\n=== All Members Overview ===");
        printHeaderLine();

        for (Person person : members) {
            if (person instanceof CompetitiveSwimmer) {
                ui.printSwimmerInfo((CompetitiveSwimmer) person);
            } else if (person instanceof Member) {
                ui.printMemberInfo((Member) person);
            }
        }
    } */


}















