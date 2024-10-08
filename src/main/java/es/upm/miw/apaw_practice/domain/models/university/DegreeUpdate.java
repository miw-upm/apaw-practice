package es.upm.miw.apaw_practice.domain.models.university;

public class DegreeUpdate {
    private Integer code;
    private Integer capacity;
    private String knowledgeArea;
    private String description;

    public DegreeUpdate() {
        //empty for framework
    }

    public DegreeUpdate(Integer code) {
        this.code = code;
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

    public DegreeUpdate setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getKnowledgeArea() {
        return knowledgeArea;
    }

    public DegreeUpdate setKnowledgeArea(String knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DegreeUpdate setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "DegreeUpdate{" +
                "code=" + code +
                ", capacity=" + capacity +
                ", knowledgeArea='" + knowledgeArea + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Degree applyTo(Degree degree) {
        if (capacity != null) {
            degree.setCapacity(capacity);
        }
        if (knowledgeArea != null) {
            degree.setKnowledgeArea(knowledgeArea);
        }
        if (description != null) {
            degree.setDescription(description);
        }
        return degree;
    }
}
