package DomainModel;

import ENUMS.MembershipStatus;
import ENUMS.SwimmerType;
import ENUMS.SwimmingDiscipline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CompetitiveSwimmer extends Member {

    //Variables
    private Team team;
    private Coach assignedCoach;
    private List<Result> results;
    private ArrayList<SwimmingDiscipline> disciplines;
    private SwimmerType swimmerType;

    //Competitive
    public CompetitiveSwimmer(String firstName, String lastName, LocalDate dateOfBirth,
                              String email, String phoneNumber, String address,
                              String memberId, MembershipStatus membershipStatus) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
        this.results = new ArrayList<>();
        this.disciplines = new ArrayList<>();
        setSwimmerType(SwimmerType.COMPETITIVE);
    }


    public void setSwimmerType(SwimmerType swimmerType) {
        this.swimmerType = swimmerType;
    }

    public CompetitiveSwimmer(String firstName, String lastName, LocalDate dateOfBirth,
                              String email, String phoneNumber, String address,
                              String memberId, MembershipStatus membershipStatus,
                              Team team, Coach assignedCoach) {
        this(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
        this.team = team;
        this.assignedCoach = assignedCoach;
    }

    // Getters
    public Team getTeam() {
        return team;
    }

    public Coach getAssignedCoach() {
        return assignedCoach;
    }

    public List<Result> getResults() {
        return new ArrayList<>(results);
    }

    public List<SwimmingDiscipline> getDisciplines() {
        return new ArrayList<>(disciplines);
    }

    public SwimmerType getSwimmerType() {
        return swimmerType;
    }

    // Setters
    public void setTeam(Team team) {
        this.team = team;
    }

    public void setAssignedCoach(Coach assignedCoach) {
        this.assignedCoach = assignedCoach;
    }

    public void setResults(List<Result> results) {
        this.results = new ArrayList<>(results);
    }

    public void setDisciplines(List<SwimmingDiscipline> disciplines) {
        this.disciplines = new ArrayList<>(disciplines);
    }

    @Override
    public String toString() {
        String teamName;
        if (team != null) {
            teamName = team.getTeamName();
        } else {
            teamName = "No Team";
        }

        String coachName;
        if (assignedCoach != null) {
            coachName = assignedCoach.getFirstName();
        } else {
            coachName = "No Coach";
        }

        return String.format("CompetitiveSwimmer{id=%s, name='%s %s', team=%s, coach=%s, disciplines=%d}",
                getMemberID(),
                getFirstName(),
                getLastName(),
                teamName,
                coachName,
                disciplines.size());
    }

}
