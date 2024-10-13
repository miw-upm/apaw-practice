package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;

public class Patient {
    private String id;
    private String name;
    private LocalDate birthDate;
    private boolean insured;
    private Appointment appointment; // Assuming Appointment is another class

    // Constructor
    public Patient(String id, String name, LocalDate birthDate, boolean insured, Appointment appointment) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.insured = insured;
        this.appointment = appointment;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public boolean isInsured() { return insured; }
    public Appointment getAppointment() { return appointment; }
}
