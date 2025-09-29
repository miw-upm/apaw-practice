package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Animal {
    private UUID animalId;
    private String petName;
    private Double weightKilos;

    public Animal(String petName, Double weightKilos) {
        this.animalId = UUID.randomUUID();
        this.petName = petName;
        this.weightKilos = weightKilos;
    }
}
