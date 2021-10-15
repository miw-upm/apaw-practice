package es.upm.miw.apaw_practice.domain.models.university;

public class SubjectCreditsUpdating {

    private Integer reference;
    private Integer credits;

    public SubjectCreditsUpdating() {
        //empty for framework
    }

    public SubjectCreditsUpdating(Integer reference, Integer credits) {
        this.reference = reference;
        this.credits = credits;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
