package es.upm.miw.apaw_practice.domain.models.wuhshu_sport;

import java.time.LocalDate;
import java.util.List;

public class Competitor {

    private String licence;
    private Integer federatedYears;
    private LocalDate lastFederationDate;
    private WushuGrade competitorGrade;
    private List<CompetitionForm> competitionForms;

    public Competitor() {
        //empty for framework
    }
    public Competitor(String licence, Integer federatedYears, LocalDate lastFederationDate, WushuGrade grade, List<CompetitionForm> competitionForms) {
        this.licence = licence;
        this.federatedYears = federatedYears;
        this.lastFederationDate = lastFederationDate;

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

    public LocalDate getLastFederatedDate() {
        return lastFederationDate;
    }

    public void setLastFederatedDate(LocalDate lastFederatedDate) {
        this.lastFederationDate = lastFederatedDate;
    }

    public WushuGrade getCompetitorGrade() {
        return competitorGrade;
    }

    public void setCompetitorGrade(WushuGrade competitorGrade) {
        this.competitorGrade = competitorGrade;
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
                ", competitorGrade=" + competitorGrade +
                ", competitionForms=" + competitionForms +
                '}';
    }
}