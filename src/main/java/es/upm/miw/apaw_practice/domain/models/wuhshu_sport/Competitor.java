package es.upm.miw.apaw_practice.domain.models.wuhshu_sport;

import java.time.LocalDate;
import java.util.List;

public class Competitor {

    private String licence;
    private Integer federatedYears;
    private LocalDate lastFederationDate;
    private WushuGrade wushuGrade;
    private List<CompetitionForm> competitionForms;

    public Competitor() {
        //empty for framework
    }
    public Competitor(String licence, Integer federatedYears, LocalDate lastFederationDate, WushuGrade grade, List<CompetitionForm> competitionForms) {
        this.licence = licence;
        this.federatedYears = federatedYears;
        this.lastFederationDate = lastFederationDate;
        this.wushuGrade = grade;
        this.competitionForms = competitionForms;
    }


    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Integer getFederatedYears() {
        return federatedYears;
    }

    public void setFederatedYears(Integer federatedYears) {
        this.federatedYears = federatedYears;
    }

    public LocalDate getLastFederationDate() {
        return lastFederationDate;
    }

    public void setLastFederationDate(LocalDate lastFederationDate) {
        this.lastFederationDate = lastFederationDate;
    }

    public WushuGrade getWushuGrade() {
        return wushuGrade;
    }

    public void setWushuGrade(WushuGrade wushuGrade) {
        this.wushuGrade = wushuGrade;
    }

    public List<CompetitionForm> getCompetitionForms() {
        return competitionForms;
    }

    public void setCompetitionForms(List<CompetitionForm> competitionForms) {
        this.competitionForms = competitionForms;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (licence.equals(((Competitor) obj).licence));
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "licence='" + licence + '\'' +
                ", federatedYears=" + federatedYears +
                ", lastFederationDate=" + lastFederationDate +
                ", wushuGrade=" + wushuGrade +
                ", competitionForms=" + competitionForms +
                '}';
    }


}
