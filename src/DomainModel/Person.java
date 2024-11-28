package DomainModel;
import ENUMS.MembershipStatus;
import java.time.LocalDate;
import java.time.Period;

//Person variables
public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected LocalDate dateOfBirth;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected String memberId;
    protected MembershipStatus membershipStatus;


    //Person constructor
    public Person(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address,
                  String memberId, MembershipStatus membershipStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.memberId = memberId;
        this.membershipStatus = membershipStatus;

    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getMemberId() {
        return memberId;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }


    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    //Methods to change current membership status of member
    public void changeMembershipToPassive(){
        this.setMembershipStatus(MembershipStatus.PASSIVE);
    }

    public void changeMembershipToActive(){
        this.setMembershipStatus(MembershipStatus.ACTIVE);
    }

    public int calculateAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                ", Date of Birth: " + dateOfBirth +
                ", Email: " + email +
                ", Phone: " + phoneNumber +
                ", Address: " + address +
                ", Member ID: " + memberId +
                ", Membership Status: " + membershipStatus;

    }
}



