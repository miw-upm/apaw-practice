package es.upm.miw.apaw_practice.domain.models.gym;

public class Athlete {
    private String athleteDni;
    private  String athleteName;
    private  String atheleFamilyname;
    private String athleteAddress;

    public Athlete(){
        //empty for framework
    }

    public Athlete(String athleteDni, String athleteName, String atheleFamilyname, String athleteAddress) {
        this.athleteDni = athleteDni;
        this.athleteName = athleteName;
        this.atheleFamilyname = atheleFamilyname;
        this.athleteAddress = athleteAddress;
    }

    public String getAthleteDni() {
        return athleteDni;
    }

    public void setAthleteDni(String athleteDni) {
        this.athleteDni = athleteDni;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAtheleFamilyname() {
        return atheleFamilyname;
    }

    public void setAtheleFamilyname(String atheleFamilyname) {
        this.atheleFamilyname = atheleFamilyname;
    }

    public String getAthleteAddress() {
        return athleteAddress;
    }

    public void setAthleteAddress(String athleteAddress) {
        this.athleteAddress = athleteAddress;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "athleteDni='" + athleteDni + '\'' +
                ", athleteName='" + athleteName + '\'' +
                ", atheleFamilyname='" + atheleFamilyname + '\'' +
                ", athleteAddress='" + athleteAddress + '\'' +
                '}';
    }
}
