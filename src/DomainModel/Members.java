package DomainModel;

import ENUMS.MembershipStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Members extends Person{
    ArrayList<Members> membersList = new ArrayList<>();
    private boolean isActive;
    private boolean isPassive;
    private double membershipFee;
    private boolean isInArrears;

    public Members(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberId, MembershipStatus membershipStatus, boolean isActive, boolean isPassive) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
        this.isActive = isActive;
        this.isPassive = isPassive;
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

           // Sørger for at vise om et medlemsskab er aktivt.
    public boolean isActive(){
        return isActive;
    }
       // Kan bruges til at sætte et medlems abonnemnt som aktivt
    public void setActive(boolean active){
        isActive = active;
        membershipFee = calculateMembershipFee(calculateAge());
    }
           // Sørger for at vise om et medlems medlemskab er passivt
    public boolean isPassive(){
        return isPassive;
    }
          // Kan bruges til at lave et medlems medlemskab til passivt
    public void setPassive(boolean passive){
        isPassive = passive;
        membershipFee = calculateMembershipFee(calculateAge());
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

    public void addMember(Members members){
        membersList.add(members);
    }
      // toString metode der gør at teksten bliver príntet rigtigt ud
    public String toString(){
        return super.toString() + ", Membership Status: " + getMembershipStatus() +
                ", Membership Fee: " + membershipFee + ", In Arrears: " + isInArrears;
    }



}
