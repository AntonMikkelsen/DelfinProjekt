package datasource;

import domainmodel.*;
import ui.UserInterface;

import java.util.ArrayList;


public class Controller {
    private MembershipRegistration Memberships = new MembershipRegistration();


    public void addMember(Member member) {
        Memberships.addMember(member);
    }

    public ArrayList<CompetitiveSwimmer> getCompSwimmers() {
        return Memberships.getAllCompetitiveSwimmers();
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

    public void addMemberToTeam(Member newMember) {
        Memberships.addMemberToTeam(newMember, );
    }
}