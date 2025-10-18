package es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class MembershipEntity {

    @Id
    private UUID id;

    private BigDecimal monthlyFee;
    private LocalDate activationDate;
    private Boolean isCurrentlyActive;

    private UUID userId;

    public Membership toMembership() {
        return Membership.builder()
                .id(this.id)
                .monthlyFee(this.monthlyFee)
                .activationDate(this.activationDate)
                .isCurrentlyActive(this.isCurrentlyActive)
                .user(UserDto.builder().id(this.userId).build())
                .build();
    }
}
