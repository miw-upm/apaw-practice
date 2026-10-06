package es.upm.miw.apaw.domain.model.credentials;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Credential {

    @EqualsAndHashCode.Include
    private UUID id;

    private String number;

    private String registryCode;

    private String authority;

    private LocalDate issueDate;

    private LocalDate expirationDate;

    private Integer renewalCount;

    private Boolean renewable;

    private CredentialType credentialType;

    private List<Verification> verifications;

    private UserSnapshot user;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.renewalCount == null) {
            this.renewalCount = 0;
        }
        if (this.renewable == null) {
            this.renewable = true;
        }
    }

    public boolean isExpired() {
        return this.expirationDate != null
                && this.expirationDate.isBefore(LocalDate.now());
    }

    public Credential ofSummary() {
        return Credential.builder()
                .id(this.id)
                .number(this.number)
                .registryCode(this.registryCode)
                .authority(this.authority)
                .issueDate(this.issueDate)
                .expirationDate(this.expirationDate)
                .renewalCount(this.renewalCount)
                .renewable(this.renewable)
                .credentialType(this.credentialType)
                .user(this.user == null ? null : UserSnapshot.builder()
                        .id(this.user.getId())
                        .mobile(this.user.getMobile())
                        .firstName(this.user.getFirstName())
                        .familyName(this.user.getFamilyName())
                        .email(this.user.getEmail())
                        .identity(this.user.getIdentity())
                        .city(this.user.getCity())
                        .build())
                .build();
    }
}