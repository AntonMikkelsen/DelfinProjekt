package DomainModel;

import ENUMS.MembershipStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member extends Person{
    ArrayList<Member> membersList = new ArrayList<>();
    private boolean isActive;
private boolean isPassive;
private double membershipFee;
private boolean isInArrears;

    public Member(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberId, MembershipStatus membershipStatus, boolean isActive, boolean isPassive) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
        this.isActive = isActive;
        this.isPassive = isPassive;
        this.membershipFee = calculateMembershipFee();
        this.isInArrears = false;
    }


    // Bruges til at udregne rabatter, hvis medlemmer feks er over 60 år gammel eller under 18.
           // isPassive er hvis deres medlemskab er passiv, der for koster det 500
    private double calculateMembershipFee(){
        int age = calculateAge();
        if(isPassive){
            return 500.00;
        }else if(age >= 60){
            return 1600.00 * 0.75;
        } else if (age < 18){
            return 1000.00;
        } else {
            return 1600.00;
        }
    }

           // Sørger for at vise om et medlemsskab er aktivt.
    public boolean isActive(){
        return isActive;
    }
       // Kan bruges til at sætte et medlems abonnemnt som aktivt
    public void setActive(boolean active){
        isActive = active;
        membershipFee = calculateMembershipFee();
    }
           // Sørger for at vise om et medlems medlemskab er passivt
    public boolean isPassive(){
        return isPassive;
    }
          // Kan bruges til at lave et medlems medlemskab til passivt
    public void setPassive(boolean passive){
        isPassive = passive;
        membershipFee = calculateMembershipFee();
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
      // toString metode der gør at teksten bliver príntet rigtigt ud
    public String toString(){
        return super.toString() + "MembershipStatus" + getMembershipStatus() +
                ", Membership Fee: " + membershipFee + ", In Arrears: " + isInArrears;
    }



}
