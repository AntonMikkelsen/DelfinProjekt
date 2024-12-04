package domainmodel;

import java.time.LocalDate;

public class Result {
    private SwimmingDiscipline discipline;
    private double time;
    private LocalDate localDate;
    private CompetitiveSwimmer swimmer; // Reference to the swimmer


    public Result(SwimmingDiscipline discipline, double time, LocalDate localDate, CompetitiveSwimmer swimmer) {
        this.discipline = discipline;
        this.time = time;
        this.localDate = localDate;
        this.swimmer = swimmer;
    }

    public SwimmingDiscipline getDiscipline(){
        return discipline;
    }


    public double getTime(){
        return time;
    }

    public LocalDate getLocalDate(){
        return localDate;
    }

    public void setDiscipline(SwimmingDiscipline discipline){
        this.discipline = discipline;
    }

    public void setTime(double time){
        this.time = time;
    }

    public void setLocalDate(LocalDate localDate){
        this.localDate = localDate;
    }

    public CompetitiveSwimmer getSwimmer() {
        return swimmer;
    }

    @Override
    public String toString() {
        return "========== Result ==========\n" + discipline + " time: " + time + "\nTime achieved: " + localDate;
    }


}
