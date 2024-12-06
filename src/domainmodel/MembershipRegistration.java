package domainmodel;

import datasource.FileHandler;

import java.util.ArrayList;

public class MembershipRegistration {
    private ArrayList<Member> membersList;
    private ArrayList<Team> teams;
    private FileHandler fileHandler;

    public MembershipRegistration() {
        this.fileHandler = new FileHandler();
        this.membersList = fileHandler.loadMembersFromFile("members.csv");
        this.teams = new ArrayList<>();
    }

    // Hent alle medlemmer
    public ArrayList<Member> getAllMembers() {
        return new ArrayList<>(membersList); // Returner en kopi for at beskytte listen
    }

    // Tilføj nyt medlem
    public boolean addMember(Member member) {
        if (!membersList.contains(member)) {
            membersList.add(member);
            fileHandler.saveMembersToFile(membersList, "members.csv");
            return true;
        }
        return false; // Return false, hvis medlemmet allerede findes
    }

    // Fjern medlem
    public boolean removeMember(Member member) {
        if (membersList.remove(member)) {
            fileHandler.saveMembersToFile(membersList, "members.csv");
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
