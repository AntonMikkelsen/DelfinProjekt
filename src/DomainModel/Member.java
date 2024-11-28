package DomainModel;

import ENUMS.MembershipStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member extends Person{
    private String memberID;
    private Enum<MembershipStatus> membershipStatus;
    ArrayList<Member> membersList = new ArrayList<>();
    private double membershipFee;
    private boolean isInArrears;


    public Member(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberID, MembershipStatus membershipStatus) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address);
        this.memberID = memberID;
        this.membershipStatus = membershipStatus;
        this.membershipFee = calculateMembershipFee(calculateAge());
        this.isInArrears = false;
    }


    // Bruges til at udregne rabatter, hvis medlemmer feks er over 60 år gammel eller under 18.
           // isPassive er hvis deres medlemskab er passiv, der for koster det 500
    public double calculateMembershipFee(int age){

        // Costs of all different membership
        final int costOfNormalSeniorMembership = 1600;
        final int costOfJuniorMembership = 1000;
        final int costOfPassiveMembership = 500;
        int costOfAbove60Membership;

        if (this.getMembershipStatus() == MembershipStatus.PASSIVE) {
            return costOfPassiveMembership;
        }

        if(age < 18) {
            return costOfJuniorMembership;
        }

        if (age >= 60) {
            costOfAbove60Membership = (int) (1600 * 0.75);
            return costOfAbove60Membership;
        }

        return costOfNormalSeniorMembership; //Den normale seniorpris over 18, under 60 år.
    }



    public double getMembershipFee(){
        return membershipFee;
    }

    public boolean isInArrears(){
        return isInArrears;
    }

    public void setInArrears(boolean inArrears){
        isInArrears = inArrears;
    }

    public void addMember(Member members){
        membersList.add(members);
    }

    public void removeMembers(Member members){
        membersList.remove(members);
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberID(){
        return memberID;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    //Methods to change current membership status of member
    public void changeMembershipToPassive(){
        this.setMembershipStatus(MembershipStatus.PASSIVE);
    }

    public void changeMembershipToActive(){
        this.setMembershipStatus(MembershipStatus.ACTIVE);
    }

    public Enum getMembershipStatus(){
        return membershipStatus;
    }

      // toString metode der gør at teksten bliver príntet rigtigt ud
    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                ", Date of Birth: " + dateOfBirth +
                ", Email: " + email +
                ", Phone: " + phoneNumber +
                ", Address: " + address +
                ", Member ID: " + memberID +
                ", Membership Status: " + membershipStatus +
                ", Membership Fee: " + membershipFee + ", In Arrears: " + isInArrears;


        }
    }
