package es.upm.miw.apaw.domain.models.winery;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wine {
    private UUID id;
    private String name;
    private Integer year;
    private Double alcoholPercentage;
    private BigDecimal price;
}
