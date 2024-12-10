package domainmodel;

import java.util.ArrayList;

public class Team {
    private final String teamName;
    private AgeCategory ageCategory;
    private ArrayList<Person> allSwimmers = new ArrayList<>();


    public Team(String teamName, AgeCategory ageCategory) {
        this.teamName = teamName;
        this.ageCategory = ageCategory;
    }

    public Team(String teamName) {
        this.teamName = teamName;
        this.allSwimmers = new ArrayList<>();
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
        return false; // For casual teams, this check is bypassed
    }

    public String getTeamName() {
        return teamName;
    }


    public void addSwimmersToTeam(Person member) {
        // Check if the swimmer is already on the team
        if (!allSwimmers.contains(member)) {
            // Casual teams accept all swimmers regardless of age
            if (ageCategory == null || isAgeCategoryValid(member)) {
                allSwimmers.add(member);
                System.out.println("You have now added " + member.getFirstName() + " " + member.getLastName() + " to " + getTeamName());

                // Handle competitive swimmer logic
                if (member instanceof CompetitiveSwimmer) {
                    ((CompetitiveSwimmer) member).setTeam(this);
                    System.out.println(member.getFirstName() + " has been added to the competitive team for " + getTeamName());
                }
            } else {
                // Error: swimmer doesn't match the team's age category
                System.out.println("Error: " + member.getFirstName() + " " + member.getLastName() +
                        " does not meet the age criteria for the " + getTeamName() + " team.");
            }
        } else {
            // Error: swimmer is already on the team
            member.getLastName();
        }
    }



    //Chatgpt's bedre version af min egen kode
    public ArrayList<CompetitiveSwimmer> getTeamMembersComp() {
        ArrayList<CompetitiveSwimmer> teamMembersTemp = new ArrayList<>();

        //Checks if team is competitive
        if (this.getTeamName().contains("Competitive")) {
            for (Person swimmer : allSwimmers) {
                if (swimmer instanceof CompetitiveSwimmer) {
                    teamMembersTemp.add((CompetitiveSwimmer) swimmer);
                }
            }
        }
        // Skal stadig fixes??
        if (this.getTeamName().contains("Casual")) {
            System.out.println("Method cannot be used on casual swimmer team");

        } else if (teamMembersTemp.isEmpty()) {
            System.out.println("No Swimmers found");

        } else {
            return teamMembersTemp;
        }
        return teamMembersTemp;
    }


    // taget koden ovenfra men lavet om på den
    //Tjekker det samme som getTeamMembersComp men lavet til casual svømmere
    public ArrayList<Member> getTeamMembersRegular() {
        ArrayList<Member> teamMembersTemp = new ArrayList<>();

        //Checks if team is competitive
        if (this.getTeamName().contains("Casual")) {
            for (Person swimmer : allSwimmers) {
                if (swimmer instanceof Member) {
                    teamMembersTemp.add((Member) swimmer);
                }
            }
        }
        // Skal stadig fixes
        if (this.getTeamName().contains("Competitive")) {
            System.out.println("Method cannot be used on competitive swimmer team");

        } else if (teamMembersTemp.isEmpty()) {
            System.out.println("No Swimmers found");

        } else {
            return teamMembersTemp;
        }
        return teamMembersTemp;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", ageCategory=" + (ageCategory != null ? ageCategory : "CASUAL") +
                ", swimmerCount=" + allSwimmers.size() +
                '}';
    }

    public String getCompTeamCategory(){
        String result = "";
        if (this.getTeamName().contains("Competitive") && this.allSwimmers.get(0).calculateAge() < 18){
            result = "Competitive Junior team";
        } else if (this.getTeamName().contains("Competitive") && this.allSwimmers.get(0).calculateAge() >= 18){
            result = "Competitive Senior Team";
        }
        return result;
    }

    public void printAllSwimmers() {
        System.out.println("Team: " + teamName);
        for (Person swimmer : allSwimmers) {
            System.out.println("- " + swimmer.getFirstName() + " " + swimmer.getLastName());
        }
    }

    public void setCoach(Coach coach1) {
    }




}
