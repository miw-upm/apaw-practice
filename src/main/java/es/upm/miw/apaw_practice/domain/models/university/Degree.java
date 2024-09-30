package es.upm.miw.apaw_practice.domain.models.university;

public class Degree {
    private int maxStudents;
    private String knowledgeArea;
    private String description;

    public Degree() {
        //empty for framework
    }

    public Degree(int maxStudents, String knowledgeArea, String description) {
        this.maxStudents = maxStudents;
        this.knowledgeArea = knowledgeArea;
        this.description = description;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
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
                "maxStudents=" + maxStudents +
                ", knowledgeArea='" + knowledgeArea + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
