package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw.domain.models.clinic.Animal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class AnimalEntity {

    // Identificador técnico de MongoDB (ID)
    @Id
    private String id; // Usamos 'id' en lugar de 'animalId' para ser estándar

    // Clave de negocio (copiada del Modelo Animal)
    private Long microchipNumber;

    private String petName;
    private Double weightKilos;
    private Boolean vaccinated;

    // Clave foránea al Doctor
    private Long doctorLicenseNumber;

    // Constructor que mapea el Modelo de Dominio (Animal) a la Entidad (AnimalEntity)
    public AnimalEntity(Animal animal) {


        this.microchipNumber = animal.getMicrochipNumber();
        this.petName = animal.getPetName();
        this.weightKilos = animal.getWeightKilos();
        this.vaccinated = animal.getVaccinated();
        this.doctorLicenseNumber = animal.getDoctorLicenseNumber();
    }

    // Método para mapear la Entidad (AnimalEntity) de vuelta al Modelo de Dominio (Animal)
    public Animal toAnimal() {
        return Animal.builder()
                .microchipNumber(this.microchipNumber)
                .petName(this.petName)
                .weightKilos(this.weightKilos)
                .vaccinated(this.vaccinated)
                .doctorLicenseNumber(this.doctorLicenseNumber)
                .build();
    }
}