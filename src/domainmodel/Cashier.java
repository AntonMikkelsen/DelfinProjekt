package domainmodel;

import java.util.ArrayList;

public class Cashier {
    ArrayList<Member> allMembers;
    ArrayList<Member> allMembersInArrears;


    public Cashier(ArrayList<Member> allMembers){
        this.allMembers = allMembers;
        this.allMembersInArrears = membersInArrears();
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

    public void addMember(Member member){
        allMembers.add(member);
    }

    public void addMemberInArrear(Member member){
        allMembersInArrears.add(member);
    }

    public void removeMemberInArrear(Member member){
        allMembersInArrears.remove(member);
    }

    public void removeMember(Member member){
        allMembers.remove(member);
    }


    public ArrayList<Member> membersInArrears(){
        ArrayList<Member> membersIsInArrears = new ArrayList<>();

        for (Member member : allMembers){
            if(member.isInArrears()) {
                membersIsInArrears.add(member);
            }
        }
        return membersIsInArrears;
    }


    //Denne metode virker pga. den bruger den anden membershipFee metode der tager cashieren som holder fast på alle de medlemmer der findes. uden denne metode vil det altid returnerer nul da den ikke holder på nogen mennesker
    public double getTotalOfDebt(){
        double totalDebt = 0;

        for (Member member : allMembersInArrears){
            totalDebt += member.getMembershipFee1(this);
        }
        return totalDebt;
    }

    /* Denne metode virker ikke da den kun udregner membershipFee for hvert person uden rigitg at tilføje det nogen stedet
    public double getTotalOfDebt(){
        double totalDebt = 0;

        for (Member member : allMembersInArrears){
            totalDebt += member.getMembershipFee();
        }
        return totalDebt;
    }

     */


    public double getTotalGeneratedIncome(){
        double totalMoney = 0;

        //Calculates all money generated from paid members
        for(Member member : allMembers){
            totalMoney += member.getMembershipFee1(this);
        }
        return totalMoney;
    }



}
