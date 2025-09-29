package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class AnimalEntity {
    @Id
    private String animalId;
    private String petName;
    private Double weightKilos;
    // Nota: Para la relación n..n con Doctor, si fuese navegable en esta dirección,
    // se necesitaría List<String> doctorIds;

    public AnimalEntity(Animal animal) {
        this.animalId = animal.getAnimalId().toString();
        this.petName = animal.getPetName();
        this.weightKilos = animal.getWeightKilos();
    }
}