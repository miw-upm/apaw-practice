package es.upm.miw.apaw.domain.models.clothingstore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @NotNull
    private UUID storeId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;
}

