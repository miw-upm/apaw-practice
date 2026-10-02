package es.upm.miw.apaw.domain.model.judicialcourt;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JudicialCourt {

    @EqualsAndHashCode.Include
    private UUID id;

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
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private JudicialCourtType type;

    private JudicialCourtStatus status;

    private List<UserSnapshot> lawyers;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = JudicialCourtStatus.ACTIVE;
        }
    }

    public JudicialCourt ofSummary() {
        return JudicialCourt.builder()
                .id(this.id)
                .name(this.name)
                .number(this.number)
                .address(this.address)
                .city(this.city)
                .postalCode(this.postalCode)
                .phone(this.phone)
                .email(this.email)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .type(this.type)
                .status(this.status)
                .lawyers(this.lawyers == null ? null : this.lawyers.stream()
                        .map(lawyer -> UserSnapshot.builder()
                                .id(lawyer.getId())
                                .mobile(lawyer.getMobile())
                                .firstName(lawyer.getFirstName())
                                .build())
                        .toList())
                .build();
    }
}
