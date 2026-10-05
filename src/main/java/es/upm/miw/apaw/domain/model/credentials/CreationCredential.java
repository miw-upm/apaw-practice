package es.upm.miw.apaw.domain.model.credentials;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationCredential {

    @NotBlank
    private String number;

    private String registryCode;

    @NotBlank
    private String authority;

    @NotNull
    private LocalDate issueDate;

    private LocalDate expirationDate;

    @NotNull
    private CredentialType credentialType;

    @NotNull
    private List<@NotNull UUID> verificationIds;

    @NotNull
    private UUID userId;
}