package es.upm.miw.apaw.domain.models.clothingstore;

import es.upm.miw.apaw.domain.models.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @NotNull
    private LocalDate date;

    @NotNull
    private BigDecimal total;

    private Integer itemCount;

    @NotNull
    private String status;

    @NotNull
    private String paymentMethod;


    private List<Garment> items;


    private Invoice invoice;


    private UserDto user;
}
