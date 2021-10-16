package es.upm.miw.apaw_practice.domain.models.music_manager;

public class BandActiveUpdating {

    private String bandName;
    private Boolean active;

    public BandActiveUpdating() {
        // empty for framework
    }

    public BandActiveUpdating(String bandName, Boolean active) {
        this.bandName = bandName;
        this.active = active;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BandActiveUpdating{" +
                "bandName='" + bandName + '\'' +
                ", active=" + active +
                '}';
    }
}
