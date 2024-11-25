package DomainModel;

public class MembershipFee {

    public int MembershipCost(Boolean isActive, int age){

        if (!isActive) {
            return 500;
        }

        if(age < 18) {
            return 1000;
        }

        if (age >= 60) {
            return (int) (1600 * 0.75);
         }

        return 1600; //Den normale seniortaks
    }



}
