package DomainModel;
import ENUMS.MembershipStatus;
import java.time.LocalDate;

public class Coach extends Person{

    public Coach(String firstName, String lastName, LocalDate dateOfBirth, String email, String phonenNúmber, String address, String memberId, MembershipStatus membershipsStatus) {
        super(firstName, lastName,dateOfBirth, email, phonenNúmber, address,memberId, membershipsStatus);
    } // Husk at adde extends på couch klasse og super parametre

    // protected String firstName;
    //    protected String lastName;
    //    protected LocalDate dateOfBirth;
    //    protected String email;
    //    protected String phoneNumber;
    //    protected String address;
    //    protected String memberId;
    //    protected MembershipStatus membershipStatus;
}
