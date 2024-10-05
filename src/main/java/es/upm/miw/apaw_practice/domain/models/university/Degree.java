package es.upm.miw.apaw_practice.domain.models.university;

public class Degree {
    private Integer code;
    private Integer capacity;
    private String knowledgeArea;
    private String description;

    public Degree() {
        //empty for framework
    }

    public Degree(int code, int capacity, String knowledgeArea, String description) {
        this.code = code;
        this.capacity = capacity;
        this.knowledgeArea = knowledgeArea;
        this.description = description;
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
        return this == obj || obj != null && getClass() == obj.getClass() && (code.equals(((Degree) obj).code));
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "Degree{" +
                "code=" + code +
                ", capacity=" + capacity +
                ", knowledgeArea='" + knowledgeArea + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
