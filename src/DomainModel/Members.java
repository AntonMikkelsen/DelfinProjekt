package DomainModel;

import ENUMS.MembershipStatus;

import java.time.LocalDate;

public class Members extends Person{

    public Members(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberId, MembershipStatus membershipStatus) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
    }
}
