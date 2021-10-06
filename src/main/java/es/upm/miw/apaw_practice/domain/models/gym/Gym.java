package es.upm.miw.apaw_practice.domain.models.gym;

import java.util.List;

public class Gym {
    private String address;
    private String label ;
    private String cellphone;
    private List<Coach> coach ;

    public Gym(){
        //empty for framework
    }

    public Gym(String address, String label,String cellphone, List<Coach> coach) {
        this.address = address;
        this.cellphone=cellphone;
        this.label = label;
        this.coach = coach;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public List<Coach> getCoach() {
        return coach;
    }

    public void setCoach(List<Coach> coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "address='" + address + '\'' +
                ", label='" + label + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", coach=" + coach +
                '}';
    }
}
