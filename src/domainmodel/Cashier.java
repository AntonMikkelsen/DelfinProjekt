package domainmodel;

import java.util.ArrayList;

public class Cashier {
    ArrayList<Member> allMembers;


    public Cashier(ArrayList<Member> allMembers){
        this.allMembers = allMembers;
    }


    public double calculateMembershipFee(Member member){

        // Costs of all different membership
        final double costOfNormalSeniorMembership = 1600;
        final double costOfJuniorMembership = 1000;
        final double costOfPassiveMembership = 500;
        final double seniorDiscount = 0.75;

        if (member.getMembershipStatus() == MembershipStatus.PASSIVE) {
            return costOfPassiveMembership;
        }

        if(member.calculateAge() < 18) {
            return costOfJuniorMembership;
        }

        if (member.calculateAge() >= 60) {
            return costOfNormalSeniorMembership * seniorDiscount;
        }

        return costOfNormalSeniorMembership; //Den normale seniorpris over 18, under 60 år.
    }










}
