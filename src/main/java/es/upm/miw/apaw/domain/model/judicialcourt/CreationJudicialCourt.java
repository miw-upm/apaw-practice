package es.upm.miw.apaw.domain.model.judicialcourt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationJudicialCourt {

    @NotBlank
    private String name;

    private Integer number;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @Size(max = 5)
    private String postalCode;

    private String phone;

    private String email;

    @NotNull
    private UUID typeId;

    @NotNull
    private UUID userId;

    @NotEmpty
    private List<@NotNull UUID> lawyerIds;
}
