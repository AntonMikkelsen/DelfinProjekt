package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
                              String memberId, MembershipStatus membershipStatus, Team team, Coach assignedCoach) {
        super(firstName, lastName, dateOfBirth, email, phoneNumber, address, memberId, membershipStatus);
        this.results = new ArrayList<>();
        this.disciplines = new ArrayList<>(Arrays.asList(
                SwimmingDiscipline.BACK_CRAWL,
                SwimmingDiscipline.BREAST_STROKE,
                SwimmingDiscipline.BUTTERFLY,
                SwimmingDiscipline.CRAWL)
        );
        this.team = team;
        this.assignedCoach = assignedCoach;
        setSwimmerType(SwimmerType.COMPETITIVE);
    }


    public void setSwimmerType(SwimmerType swimmerType) {
        this.swimmerType = swimmerType;
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

    //Returns as char sequence
    public CharSequence getDisciplines() {
        return (CharSequence) new ArrayList<>(disciplines);
    }


    // Returns as normal array
    public ArrayList<SwimmingDiscipline> getDisciplinesArray() {
        return disciplines;
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


    public static void printAllCompSwimmersBestDiscipline(List<CompetitiveSwimmer> swimmers) {
        for (CompetitiveSwimmer swimmer : swimmers) {
            List<SwimmingDiscipline> sortedDisciplines = new ArrayList<>(swimmer.getDisciplinesArray());

            // Sorter disciplinerne efter bedste tid
            sortedDisciplines.sort((discipline1, discipline2) -> {
                double time1 = swimmer.getBestTimeForEachDiscipline(discipline1);
                double time2 = swimmer.getBestTimeForEachDiscipline(discipline2);
                return Double.compare(time1, time2);
            });


            // Udskriv information om svømmeren og de bedste tider
            System.out.println("\n Swimmers name: " + swimmer.getFirstName() + " " + swimmer.getLastName());

            for (int i = 0; i < sortedDisciplines.size(); i++) {
                double time = swimmer.getBestTimeForEachDiscipline(sortedDisciplines.get(i));
                String timeString = (time == Double.MAX_VALUE) ? "N/A" : String.valueOf(time);

                // Best, second best, third best, etc.
                if (i == 0) {
                    System.out.println(" - Best Discipline: " + sortedDisciplines.get(i).getFullName() + " - Time: " + timeString);
                } else if (i == 1) {
                    System.out.println(" - Second Best Discipline: " + sortedDisciplines.get(i).getFullName() + " - Time: " + timeString);
                } else if (i == 2) {
                    System.out.println(" - Third Best Discipline: " + sortedDisciplines.get(i).getFullName() + " - Time: " + timeString);
                }
            }

            // Værste disciplin
            double worstTime = swimmer.getBestTimeForEachDiscipline(sortedDisciplines.get(sortedDisciplines.size() - 1));
            String worstTimeString = (worstTime == Double.MAX_VALUE) ? "N/A" : String.valueOf(worstTime);
            System.out.println(" - Worst Discipline: " + sortedDisciplines.get(sortedDisciplines.size() - 1).getFullName() + " - Time: " + worstTimeString);
        }
    }





    public void sortDisciplinesByPerformance() {
        for (int i = 0; i < disciplines.size() - 1; i++) {
            for (int j = 0; j < disciplines.size() - 1 - i; j++) {
                double time1 = getBestTimeForEachDiscipline(disciplines.get(j));
                double time2 = getBestTimeForEachDiscipline(disciplines.get(j + 1));

                // Swap if time1 is greater than time2
                if (time1 < time2) {
                    SwimmingDiscipline temp = disciplines.get(j);
                    disciplines.set(j, disciplines.get(j + 1));
                    disciplines.set(j + 1, temp);
                }
            }
        }
    }


    public double getBestTimeForEachDiscipline(SwimmingDiscipline discipline) {
        double bestTime = Double.MAX_VALUE;

        for (Result result : results) {
            if (result.getDiscipline() == discipline) {
                if (result.getTime() < bestTime) {
                    bestTime = result.getTime();
                }
            }
        }
        return bestTime;
    }


    public void addResult(Result result) {
        if (result != null) {
            this.results.add(result);
        }
    }
}


