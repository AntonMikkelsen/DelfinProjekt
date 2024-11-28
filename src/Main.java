import DomainModel.Members;
import DomainModel.Person;
import ENUMS.MembershipStatus;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Members member1 = new Members("Rikke", "Hansen", LocalDate.of(1967, 06, 05), "RikkeSnabelA", "50", "Vægterparken", "55",MembershipStatus.ACTIVE, true, false);
        Members member2 = new Members("Rikke", "Hansen", LocalDate.of(1930, 06, 05), "RikkeSnabelA", "50", "Vægterparken", "55",MembershipStatus.ACTIVE, true, false);
        Members member3 = new Members("Rikke", "Hansen", LocalDate.of(2020, 06, 05), "RikkeSnabelA", "50", "Vægterparken", "55",MembershipStatus.ACTIVE, true, false);

        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);
    }
}