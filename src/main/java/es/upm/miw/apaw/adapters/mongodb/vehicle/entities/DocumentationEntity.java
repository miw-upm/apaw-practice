package es.upm.miw.apaw.adapters.mongodb.vehicle.entities;

import es.upm.miw.apaw.domain.models.vehicle.Documentation;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentationEntity {
    private String name;
    private Boolean validate;
    private LocalDate issued;

    public Documentation toDocumentation() {
        Documentation documentation = new Documentation();
        BeanUtils.copyProperties(this, documentation);
        return documentation;
    }
}
