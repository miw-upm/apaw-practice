package es.upm.miw.apaw_practice.domain.models.hotel;

public class Director {

    private String name;
    private String DNI;
    private Long telephone;

    Director(){
        //empty for framework
    }

    Director(String name, String DNI, Long telephone){
        this.name = name;
        this.DNI = DNI;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}
