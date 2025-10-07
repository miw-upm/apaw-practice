package es.upm.miw.apaw.adapters.mongodb.vehicle.entities;

import es.upm.miw.apaw.domain.models.vehicle.Documentation;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentationEntity {
    private String name;
    private Boolean validate;

    public Documentation toDocumentation() {
        Documentation documentation = new Documentation();
        BeanUtils.copyProperties(this, documentation);
        return documentation;
    }
}
