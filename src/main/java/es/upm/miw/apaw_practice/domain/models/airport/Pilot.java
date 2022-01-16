package es.upm.miw.apaw_practice.domain.models.airport;

public class Pilot {
    private String idNumber;
    private String pilotName;
    private String pilotSurname;
    private Boolean mainPilot;

    public Pilot() {
        // empty for framework
    }

    public Pilot(String idNumber, String pilotName, String pilotSurname, Boolean mainPilot) {
        this.idNumber = idNumber;
        this.pilotName = pilotName;
        this.pilotSurname = pilotSurname;
        this.mainPilot = mainPilot;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getPilotSurname() {
        return pilotSurname;
    }

    public void setPilotSurname(String pilotSurname) {
        this.pilotSurname = pilotSurname;
    }

    public Boolean getMainPilot() {
        return mainPilot;
    }

    public void setMainPilot(Boolean mainPilot) {
        this.mainPilot = mainPilot;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "idNumber='" + this.idNumber + '\'' +
                ", pilotName='" + this.pilotName + '\'' +
                ", pilotSurname='" + this.pilotSurname + '\'' +
                ", mainPilot=" + this.mainPilot +
                '}';
    }
}
