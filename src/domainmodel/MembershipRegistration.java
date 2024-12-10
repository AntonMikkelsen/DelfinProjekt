package domainmodel;

import datasource.FileHandler;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class MembershipRegistration {
    private ArrayList<Member> membersList;
    private ArrayList<Team> teams;
    private FileHandler fileHandler;
    private Cashier cashier;

    public MembershipRegistration() {
        this.membersList = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.cashier = new Cashier(membersList); // Initialize the cashier with the members list
        fileHandler = new FileHandler();
    }


    // Hent alle medlemmer
    public ArrayList<Member> getAllMembers() {
        for (Member member : membersList) {
            System.out.println(member);
        }
        return membersList;
    }

    public void displayMembers() {
        fileHandler.loadMembersFromFile();
    }

    // Tilføj nyt medlem
    public void addMember(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberID, MembershipStatus membershipStatus) {
        Member member = new Member(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberID, membershipStatus);
        membersList.add(member);
        fileHandler.saveMembersToFile(membersList);
    }

    // Fjern medlem
    public boolean removeMember(Member member) {
        if (membersList.remove(member)) {
            return true;
        }
        return false; // Return false, hvis medlemmet ikke findes
    }

    // Hent alle konkurrerende svømmere
    public ArrayList<CompetitiveSwimmer> getAllCompetitiveSwimmers() {
        ArrayList<CompetitiveSwimmer> competitiveSwimmers = new ArrayList<>();
        for (Member member : membersList) {
            if (member instanceof CompetitiveSwimmer) {
                competitiveSwimmers.add((CompetitiveSwimmer) member);
            }
        }
        return competitiveSwimmers;
    }

    // Hent hold
    public ArrayList<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    // Tilføj nyt hold
    public boolean addTeam(Team team) {
        if (!teams.contains(team)) {
            teams.add(team);
            return true;
        }
        return false; // Return false, hvis holdet allerede findes
    }

    // Tilføj medlem til hold
    public void addMemberToTeam(Member member, Team team) {
        if (teams.contains(team)) {
            team.addSwimmersToTeam((CompetitiveSwimmer) member);
        }
    }

    public void cashierTotalIncomeGenerated() {
        cashier.getTotalGeneratedIncome();
        System.out.printf("Total money generated: %.2f%n", cashier.getTotalGeneratedIncome());
    }

    // Liste over medlemmer
    public ArrayList<String> getListOfMembers() {
        ArrayList<String> list = new ArrayList<>();
        for (Member member : membersList) {
            list.add(member.toString());
        }
        return list;
    }

    // Liste over konkurrerende svømmere
    public ArrayList<String> getListOfCompetitiveSwimmers() {
        ArrayList<String> list = new ArrayList<>();
        for (CompetitiveSwimmer swimmer : getAllCompetitiveSwimmers()) {
            list.add(swimmer.toString());
        }
        return list;
    }

    @Override
    public String toString() {
        return "MembershipRegistration{" +
                "membersListSize=" + membersList.size() +
                ", teamsSize=" + teams.size() +
                '}';
    }
}
