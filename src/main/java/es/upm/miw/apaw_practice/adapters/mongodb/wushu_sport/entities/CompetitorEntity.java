package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class CompetitorEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String licence;
    private Integer federatedYears;
    private LocalDate lastFederationDate;
    @DBRef
    private WushuGradeEntity wushuGradeEntity;
    @DBRef
    private List<CompetitionFormEntity> competitionFormsEntities;

    public CompetitorEntity() {
        //Empty for framework
    }

    public CompetitorEntity(String licence, Integer federatedYears, LocalDate lastFederationDate, WushuGradeEntity wushuGradeEntity, List<CompetitionFormEntity> competitionFormsEntities) {
        this.id = UUID.randomUUID().toString();
        this.licence = licence;
        this.federatedYears = federatedYears;
        this.lastFederationDate = lastFederationDate;
        this.wushuGradeEntity = wushuGradeEntity;
        this.competitionFormsEntities = competitionFormsEntities;
    }

    public CompetitorEntity(Competitor competitor) {
            BeanUtils.copyProperties(competitor, this);
            this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public WushuGradeEntity getWuhsuGradeEntity() {
        return wushuGradeEntity;
    }

    public void setWuhsuGradeEntity(WushuGradeEntity wushuGradeEntity) {
        this.wushuGradeEntity = wushuGradeEntity;
    }

    public List<CompetitionFormEntity> getCompetitionFormsEntities() {
        return competitionFormsEntities;
    }

    public void setCompetitionFormsEntities(List<CompetitionFormEntity> competitionFormsEntities) {
        this.competitionFormsEntities = competitionFormsEntities;
    }

    public Competitor toCompetitor(){
        List<CompetitionForm> competitionForms = this.competitionFormsEntities.stream()
                .map(CompetitionFormEntity::toCompetitionForm)
                .collect(Collectors.toList());
        WushuGrade wushuGrade = this.wushuGradeEntity.toWushuGrade();
        return new Competitor( licence,federatedYears,lastFederationDate,wushuGrade,competitionForms);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (licence.equals(((CompetitorEntity) obj).licence));
    }

    @Override
    public String toString() {
        return "CompetitorEntity{" +
                "id='" + id + '\'' +
                ", licence='" + licence + '\'' +
                ", federatedYears=" + federatedYears +
                ", lastFederationDate=" + lastFederationDate +
                ", wuhsuGradeEntity=" + wushuGradeEntity +
                ", competitionFormsEntities=" + competitionFormsEntities +
                '}';
    }
}
