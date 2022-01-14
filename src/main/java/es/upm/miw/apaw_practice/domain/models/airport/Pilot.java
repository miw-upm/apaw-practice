package es.upm.miw.apaw_practice.domain.models.airport;

public class Pilot {
    private String idNumber;
    private String pilotName;
    private String pilotSurname;
    private Boolean isMainPilot;

    public Pilot() {
        // empty for framework
    }

    public Pilot(String idNumber, String pilotName, String pilotSurname, Boolean isMainPilot) {
        this.idNumber = idNumber;
        this.pilotName = pilotName;
        this.pilotSurname = pilotSurname;
        this.isMainPilot = isMainPilot;
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
        return isMainPilot;
    }

    public void setMainPilot(Boolean mainPilot) {
        isMainPilot = mainPilot;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "idNumber='" + this.idNumber + '\'' +
                ", pilotName='" + this.pilotName + '\'' +
                ", pilotSurname='" + this.pilotSurname + '\'' +
                ", isMainPilot=" + this.isMainPilot +
                '}';
    }
}
