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
}