package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.University;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class UniversityEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String topDomain;
    private String name;
    private Boolean allowsInternationalStudents;
    private Integer numberOfFaculties;
    @DBRef
    private List<DegreeEntity> degrees;

    public UniversityEntity() {
        //empty for framework
    }

    public UniversityEntity(String topDomain, String name, Boolean allowsInternationalStudents, Integer numberOfFaculties, List<DegreeEntity> degrees) {
        this.id = UUID.randomUUID().toString();
        this.topDomain = topDomain;
        this.name = name;
        this.allowsInternationalStudents = allowsInternationalStudents;
        this.numberOfFaculties = numberOfFaculties;
        this.degrees = degrees;
    }

    public UniversityEntity(University university) {
        this.fromUniversity(university);
        this.id = UUID.randomUUID().toString();
    }

    public void fromUniversity(University university) {
        BeanUtils.copyProperties(university, this);
        this.setDegrees(university.getDegreesOffered().stream().map(DegreeEntity::new).toList());
    }

    public University toUniversity() {
        University university = new University();
        BeanUtils.copyProperties(this, university);
        university.setDegreesOffered(this.degrees.stream().map(DegreeEntity::toDegree).toList());
        return university;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopDomain() {
        return topDomain;
    }

    public void setTopDomain(String topDomain) {
        this.topDomain = topDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowsInternationalStudents() {
        return allowsInternationalStudents;
    }

    public void setAllowsInternationalStudents(Boolean allowsInternationalStudents) {
        this.allowsInternationalStudents = allowsInternationalStudents;
    }

    public Integer getNumberOfFaculties() {
        return numberOfFaculties;
    }

    public void setNumberOfFaculties(Integer numberOfFaculties) {
        this.numberOfFaculties = numberOfFaculties;
    }

    public List<DegreeEntity> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<DegreeEntity> degrees) {
        this.degrees = degrees;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (topDomain.equals(((UniversityEntity) obj).topDomain));
    }

    @Override
    public int hashCode() {
        return topDomain.hashCode();
    }

    @Override
    public String toString() {
        return "UniversityEntity{" +
                "id='" + id + '\'' +
                ", topDomain='" + topDomain + '\'' +
                ", name='" + name + '\'' +
                ", allowsInternationalStudents=" + allowsInternationalStudents +
                ", numberOfFaculties=" + numberOfFaculties +
                ", degrees=" + degrees +
                '}';
    }
}
