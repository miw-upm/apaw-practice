package es.upm.miw.apaw.domain.model.judicialcourt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JudicialCourtType {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String code;

    private String jurisdiction;

    private Boolean active;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.active == null) {
            this.active = true;
        }
    }
}
