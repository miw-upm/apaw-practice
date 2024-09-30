package es.upm.miw.apaw_practice.domain.models.university;

public class Degree {
    private int code;
    private int capacity;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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
    public String toString() {
        return "Degree{" +
                "code=" + code +
                ", capacity=" + capacity +
                ", knowledgeArea='" + knowledgeArea + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
