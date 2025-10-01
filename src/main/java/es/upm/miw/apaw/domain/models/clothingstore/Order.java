package es.upm.miw.apaw.domain.models.clothingstore;

import es.upm.miw.apaw.domain.models.UserDto;  // 按模板直接引用 UserDto
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @NotNull
    private UUID orderId;

    @NotNull
    private LocalDate orderDate;

    private BigDecimal total;


    private Integer itemCount;


    private List<Garment> items;

    private Invoice invoice;


    private Store store;


    private UserDto customer;
}
