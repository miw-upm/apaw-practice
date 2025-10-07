package es.upm.miw.apaw.adapters.mongodb.vehicle.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.vehicle.Documentation;
import es.upm.miw.apaw.domain.models.vehicle.Extra;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class VehicleEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String plate;
    private String brand;
    private String model;
    private LocalDate registrationDate;
    @DBRef
    private EngineEntity engineEntity;
    private List<DocumentationEntity> documentationEntities;
    @DBRef
    private List<ExtraEntity> extraEntities;
    private UUID owner;

    public Vehicle toVehicle() {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(this, vehicle,
                "owner", "engineEntity", "documentationEntities", "extraEntities");
        vehicle.setEngine(this.engineEntity.toEngine());
        vehicle.setDocumentations(
                this.documentationEntities.stream()
                        .map(DocumentationEntity::toDocumentation)
                        .toList()
        );
        vehicle.setExtras(
                this.extraEntities.stream()
                        .map(ExtraEntity::toExtra)
                        .toList()
        );
        vehicle.setOwner(UserDto.builder().id(owner).build());
        return vehicle;
    }
}
