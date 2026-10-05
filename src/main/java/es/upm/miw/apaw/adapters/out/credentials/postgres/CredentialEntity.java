package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CredentialType;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CredentialEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(unique = true)
    private String registryCode;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false)
    private LocalDate issueDate;

    private LocalDate expirationDate;

    @Column(nullable = false)
    private Integer renewalCount;

    @Column(nullable = false)
    private Boolean renewable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CredentialType credentialType;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "credential_id")
    private List<VerificationEntity> verifications;

    @Column(nullable = false)
    private UUID userId;

    public CredentialEntity(Credential credential) {
        BeanUtils.copyProperties(credential, this, "verifications", "user");
        this.verifications = credential.getVerifications().stream()
                .map(VerificationEntity::new)
                .toList();
        this.userId = credential.getUser().getId();
    }

    public Credential toDomain() {
        Credential credential = new Credential();
        BeanUtils.copyProperties(this, credential, "verifications", "userId");
        credential.setVerifications(new ArrayList<>(this.verifications.stream()
                .map(VerificationEntity::toDomain)
                .toList()));
        credential.setUser(UserSnapshot.builder()
                .id(this.userId)
                .build());
        return credential;
    }
}