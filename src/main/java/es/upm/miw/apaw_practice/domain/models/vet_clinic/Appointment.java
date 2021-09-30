package es.upm.miw.apaw_practice.domain.models.vet_clinic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Appointment {
    private LocalDate date;
    private LocalTime hour;
    private Boolean consumed;
    private Pet pet;
    private List<Vet> vets;

    public Appointment(){
        //empty for framework
    }

    public Appointment(LocalDate date, LocalTime hour, Boolean consumed, Pet pet, List<Vet> vets){
        this.date = date;
        this.hour = hour;
        this.consumed = consumed;
        this.pet = pet;
        this.vets = vets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Boolean getConsumed() {
        return consumed;
    }

    public void setConsumed(Boolean consumed) {
        this.consumed = consumed;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<Vet> getVets() {
        return vets;
    }

    public void setVets(List<Vet> vets) {
        this.vets = vets;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", hour=" + hour +
                ", consumed=" + consumed +
                ", pet=" + pet +
                ", vets=" + vets +
                '}';
    }
}
