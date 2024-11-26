package DomainModel;
import ENUMS.MembershipStatus;

public class MembershipFee {

    public int MembershipCost(Person person, int age){

        // Costs of all different membership
        final int costOfNormalSeniorMembership = 1600;
        final int costOfJuniorMembership = 1000;
        final int costOfPassiveMembership = 500;
        int costOfAbove60Membership;

        if (person.getMembershipStatus() == MembershipStatus.PASSIVE) {
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

}
