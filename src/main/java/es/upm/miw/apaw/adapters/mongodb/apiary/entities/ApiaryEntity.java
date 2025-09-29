package es.upm.miw.apaw.adapters.mongodb.apiary.entities;

import es.upm.miw.apaw.domain.models.apiary.Apiary;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class ApiaryEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String cadastralRef;

    private String location;
    private String rega;

    @DBRef
    private List<HiveEntity> hiveEntities;

    public ApiaryEntity(Apiary apiary) {
        BeanUtils.copyProperties(apiary, this);
        this.id = UUID.randomUUID();
        if (apiary.getHives() != null) {
            this.hiveEntities = apiary.getHives().stream()
                    .map(HiveEntity::new)
                    .toList();
        }
    }

    public Apiary toApiary() {
        Apiary apiary = new Apiary();
        BeanUtils.copyProperties(this, apiary, "hives");
        if (this.hiveEntities != null) {
            apiary.setHives(
                    this.hiveEntities.stream()
                            .map(HiveEntity::toHive)
                            .toList()
            );
        }
        return apiary;
    }
}

