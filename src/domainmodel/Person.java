package domainmodel;
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


    //Person constructor
    public Person(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        if (phoneNumber.length() != 8){
            throw new IllegalArgumentException();
        } else {
            this.phoneNumber = phoneNumber;
        }
        this.address = address;

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

    //Methods to change current membership status of member
    public int calculateAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                ", Date of Birth: " + dateOfBirth +
                ", Email: " + email +
                ", Phone: " + phoneNumber +
                ", Address: " + address;
    }

    public SwimmerType getSwimmerType() {
        return null;
    }
}



