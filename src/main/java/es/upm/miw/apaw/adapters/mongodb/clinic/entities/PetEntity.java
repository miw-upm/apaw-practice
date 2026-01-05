package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Gender;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.models.clinic.Species;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class PetEntity {

    @Id
    private String id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Long microchipNumber;
    private String name;
    private Species species;
    private Gender gender;
    private List<UUID> appointments;

    public PetEntity(Pet pet) {
        BeanUtils.copyProperties(pet, this, "appointments");

        this.microchipNumber = pet.getMicrochipNumber();
        this.name = pet.getName();
        this.species = pet.getSpecies();
        this.gender = pet.getGender();
        if (pet.getAppointments() != null) {
            this.appointments = pet.getAppointments().stream()
                    .map(Appointment::getId)
                    .toList();
        }
    }

    public Pet toPet() {
        Pet pet = new Pet();
        BeanUtils.copyProperties(this, pet, "appointments");
        if(this.getAppointments() != null) {
            pet.setAppointments(this.getAppointments()
                    .stream().map(appointmentId -> Appointment.builder().id(appointmentId).build()).toList());
        }
        return pet;
    }
}