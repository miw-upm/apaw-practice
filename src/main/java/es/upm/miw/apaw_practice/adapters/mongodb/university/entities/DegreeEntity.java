package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class DegreeEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer code;
    private Integer capacity;
    private String knowledgeArea;
    private String description;

    public DegreeEntity() {
        //empty from framework
    }

    public DegreeEntity(Degree degree) {
        this.fromDegree(degree);
        this.id = UUID.randomUUID().toString();
    }

    public void fromDegree(Degree degree) {
        BeanUtils.copyProperties(degree, this);
    }

    public Degree toDegree() {
        Degree degree = new Degree();
        BeanUtils.copyProperties(this, degree);
        return degree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(String knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (code.equals(((DegreeEntity) obj).code));
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "DegreeEntity{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", capacity=" + capacity +
                ", knowledgeArea='" + knowledgeArea + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
