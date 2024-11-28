package DomainModel;

import ENUMS.MembershipStatus;
import ENUMS.SwimmingDiscipline;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CompetitiveSwimmer extends Member {

    //Variables
    private Team team;
    private Coach assignedCoach;
    private List<Result> results;
    private Set<SwimmingDiscipline> disciplines;

    //Competitive
    public CompetitiveSwimmer(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, String address, String memberId, MembershipStatus membershipStatus) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
    }

    // Getters
    public Team getTeam() {
        return team;
    }

    public Coach getAssignedCoach() {
        return assignedCoach;
    }

    public List<Result> getResults() {
        return results;
    }

    public Set<SwimmingDiscipline> getDisciplines() {
        return disciplines;
    }

    //Setters
    public void setTeam(Team team) {
        this.team = team;
    }

    public void setAssignedCoach(Coach assignedCoach) {
        this.assignedCoach = assignedCoach;
    }


    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void setDisciplines(Set<SwimmingDiscipline> disciplines) {
        this.disciplines = disciplines;
    }
}
