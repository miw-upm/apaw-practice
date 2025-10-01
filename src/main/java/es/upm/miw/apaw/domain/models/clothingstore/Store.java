package es.upm.miw.apaw.domain.models.clothingstore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Store {

    @NotNull
    private UUID storeId;

    @NotNull
    @NotBlank
    private String name;

    private String address;

    /** aggregation: 1..n */
    private List<Garment> garments;

    /** composition: 1..n */
    private List<Order> orders;
}
