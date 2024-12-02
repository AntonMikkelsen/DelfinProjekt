package DomainModel;

import ENUMS.MembershipStatus;
import ENUMS.SwimmerType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Member extends Person{
    private String memberID;
    private Enum<MembershipStatus> membershipStatus;
    ArrayList<Member> membersList = new ArrayList<>();
    private double membershipFee;
    private boolean isInArrears;
    private SwimmerType swimmerType;


    public Member(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberID, MembershipStatus membershipStatus) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address);
        this.memberID = memberID;
        this.membershipStatus = membershipStatus;
        setSwimmerType(SwimmerType.CASUAL);
        this.membershipFee = calculateMembershipFee(calculateAge());
        this.isInArrears = false;
    }

    private String generateMemberIDRandom(String firstName, String lastName) {
        Random random = new Random();

        String firstInitials;
        if (firstName.length() >= 2) {
            firstInitials = firstName.substring(0, 2).toUpperCase();
        } else {
            firstInitials = firstName.substring(0, 1).toUpperCase();
        }

        String lastInitials;
        if (lastName.length() >= 2) {
            lastInitials = lastName.substring(0, 2).toUpperCase();
        } else {
            lastInitials = lastName.substring(0, 1).toUpperCase();
        }

        int randomNum = random.nextInt(8999) + 1000;

        return firstInitials + lastInitials + randomNum;
    }


    // Bruges til at udregne rabatter, hvis medlemmer fx er over 60 år gammel eller under 18.
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

    public void addMember(){
        membersList.add(this);
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

    public void setSwimmerType(SwimmerType swimmerType) {
        this.swimmerType = swimmerType;
    }

    // Add this getter method
    public SwimmerType getSwimmerType() {
        return swimmerType;
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
