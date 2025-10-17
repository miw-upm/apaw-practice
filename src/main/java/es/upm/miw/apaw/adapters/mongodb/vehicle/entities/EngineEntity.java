package es.upm.miw.apaw.adapters.mongodb.vehicle.entities;

import es.upm.miw.apaw.domain.models.vehicle.Engine;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class EngineEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String codeEngine;
    private String type;
    private Double displacement;

    public EngineEntity(Engine engine) {
        this.id = UUID.randomUUID();
        BeanUtils.copyProperties(engine, this);
    }

    public Engine toEngine() {
        Engine engine = new Engine();
        BeanUtils.copyProperties(this, engine);
        return engine;
    }
}
