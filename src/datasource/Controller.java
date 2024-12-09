package datasource;

import domainmodel.*;
import ui.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;


public class Controller {
    private MembershipRegistration Memberships = new MembershipRegistration();


    public Member addMember(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberID, MembershipStatus membershipStatus) {
        Memberships.addMember(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberID, membershipStatus);
        return null;
    }

    public ArrayList<CompetitiveSwimmer> getCompSwimmers() {
        return Memberships.getAllCompetitiveSwimmers();
    }

    public void displayMembers(){
        Memberships.displayMembers();
    }

    public ArrayList<Member> getMembers() {
        return Memberships.getAllMembers();
    }

    public ArrayList<String> listOfAllMembers() {
        return Memberships.getListOfMembers();
    }

    public ArrayList<String> listOfComp() {
        return Memberships.getListOfCompetitiveSwimmers();
    }

    /* udkommenteret så programmet kan køre - skal stadig bruges tror jeg
    public void addMemberToTeam(Member newMember) {
        Memberships.addMemberToTeam(newMember,  );
    }

     */
}