package domainmodel;

import java.time.LocalDate;

public class Coach extends Person{
    private String CoachID;
    private Team assignedTeam;

    public Coach(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String coachID, Team assignedTeam) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address);
        this.CoachID = coachID;
        this.assignedTeam = assignedTeam;
    }





        @Override
        public String toString() {
            return "Name: " + firstName + " " + lastName +
                    ", Date of Birth: " + dateOfBirth +
                    ", Email: " + email +
                    ", Phone: " + phoneNumber +
                    ", Address: " + address +
                    ", Coach ID: " + CoachID +
                    ", Assigned Team: " + assignedTeam;

    }
}
