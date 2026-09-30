package es.upm.miw.apaw.domain.model.training;

import java.time.LocalDate;

public class Course {
    private String id;
    private String name;
    private String certificateReference;
    private int durationHours;
    private boolean online;
    private LocalDate launchDate;

    public Course(String id, String name, String certificateReference, int durationHours, boolean online, LocalDate launchDate) {
        this.id = id;
        this.name = name;
        this.certificateReference = certificateReference;
        this.durationHours = durationHours;
        this.online = online;
        this.launchDate = launchDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateReference() {
        return certificateReference;
    }

    public void setCertificateReference(String certificateReference) {
        this.certificateReference = certificateReference;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }
}
