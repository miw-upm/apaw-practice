package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities;



import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MartialArtsClassEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LocalDate startDate;
    private String academy;
    @DBRef
    private List<TechniqueEntity> techniqueList;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }
    public List<TechniqueEntity> getTechniqueList() {
        return techniqueList;
    }

    public void setTechniqueList(List<TechniqueEntity> techniqueList) {
        this.techniqueList = techniqueList;
    }
    public MartialArtsClassEntity(String name, LocalDate startDate, String academy, List<TechniqueEntity> techniqueList) {
        this.name = name;
        this.startDate = startDate;
        this.academy = academy;
        this.techniqueList = techniqueList;
    }

    public MartialArtsClass toMartialArtsClass() {
        List<Technique> techniques = this.techniqueList.stream()
                .map(TechniqueEntity::toTechnique)
                .toList();
        return new MartialArtsClass(name, startDate, academy, techniques);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MartialArtsClassEntity that = (MartialArtsClassEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(startDate, that.startDate) && Objects.equals(academy, that.academy) && Objects.equals(techniqueList, that.techniqueList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, academy, techniqueList);
    }

    @Override
    public String toString() {
        return "MartialArtsClassEntity{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", academy='" + academy + '\'' +
                ", techniqueList=" + techniqueList +
                '}';
    }
}
