package es.upm.miw.apaw_practice.domain.models.Hospital;

public class Paciente{
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Boolean hasInsurance;

    public Paciente(){

    }

    public Paciente(Long id, String name,LocalDate dateOfBirth ,Boolean hasInsurance){
        this.id = id;
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.hasInsurance=hasInsurance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }
}