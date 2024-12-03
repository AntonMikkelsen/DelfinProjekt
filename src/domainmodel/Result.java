package domainmodel;

import java.time.LocalDate;

public class Result {
    private SwimmingDiscipline discipline;
    private double time;
    private LocalDate localDate;


    public Result(SwimmingDiscipline discipline, double time, LocalDate localDate) {
        this.discipline = discipline;
        this.time = time;
        this.localDate = localDate;
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

}
