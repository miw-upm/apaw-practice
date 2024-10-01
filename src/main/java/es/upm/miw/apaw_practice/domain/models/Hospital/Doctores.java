package es.upm.miw.apaw_practice.domain.models.Hospital;

public class Doctores{
    private Long id;
    private String name;
    private String specialization;
    private BigDecimal slary;

    public Doctores() {
    }

    public Doctores(Long id, String name, String specialization, BigDecimal slary) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.slary = slary;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public BigDecimal getSlary() {
        return slary;
    }

    public void setSlary(BigDecimal slary) {
        this.slary = slary;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Doctores{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", slary=" + slary +
                '}';
    }
}