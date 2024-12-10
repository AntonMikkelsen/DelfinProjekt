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

    public void cashierTotalIncomeGenerated(){
        Memberships.cashierTotalIncomeGenerated();
    }

    public void addMembersToArrearsController(Member member, boolean boolean1){
        Memberships.addMemberToArrears(member, boolean1);
    }

    public void cashierTotalDebtFromArrears(){
        Memberships.cashierTotalDebtFromArrears();
    }

    public void cashierNetIncomeNoArrears(){
        Memberships.netIncomeWithoutArrearsMoney();
    }


    public void addMemberToTeam(Member newMember) {
        Memberships.addMemberToTeam(newMember,new Team("Pop"));
    }
}