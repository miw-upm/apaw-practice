package es.upm.miw.apaw_practice.domain.models.competition;

import java.time.LocalDate;

public class Competition {

    private String nameCompetition;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isInternational;

    public Competition() {
        // empty for framework
    }

    public Competition(String nameCompetition, LocalDate startDate, LocalDate endDate, boolean isInternational) {
        this.nameCompetition = nameCompetition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isInternational = isInternational;
    }

    public String getNameCompetition() {
        return nameCompetition;
    }

    public void setNameCompetition(String nameCompetition) {
        this.nameCompetition = nameCompetition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "nameCompetition='" + nameCompetition + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isInternational=" + isInternational +
                '}';
    }
}
