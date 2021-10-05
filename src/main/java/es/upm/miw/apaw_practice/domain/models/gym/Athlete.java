package es.upm.miw.apaw_practice.domain.models.gym;

public class Athlete {
    private String nie;
    private  String name;
    private  String familyName;

    public Athlete(){
        //empty for framework
    }

    public Athlete(String nie, String name, String familyName) {
        this.nie = nie;
        this.name = name;
        this.familyName = familyName;
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyname(String familyname) {
        this.familyName = familyname;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "nie='" + nie + '\'' +
                ", name='" + name + '\'' +
                ", familyname='" + familyName + '\'' +
                '}';
    }
}
