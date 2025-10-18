package es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities;

import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class DojoEntity {

    @Id
    @Indexed(unique = true)
    @EqualsAndHashCode.Include
    private String cadastralReference;

    private String city;
    private LocalDate foundationDate;

    @DBRef
    private List<EquipmentEntity> equipment;

    @DBRef
    private List<ClassSessionEntity> classSessions;

    public Dojo toDojo() {
        return Dojo.builder()
                .cadastralReference(this.cadastralReference)
                .city(this.city)
                .foundationDate(this.foundationDate)
                .equipment(this.equipment == null ? List.of() :
                        this.equipment.stream().map(EquipmentEntity::toEquipment).toList())
                .build();
    }
}
