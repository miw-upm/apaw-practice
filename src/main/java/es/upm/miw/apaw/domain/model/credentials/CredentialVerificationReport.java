package es.upm.miw.apaw.domain.model.credentials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialVerificationReport {
    private String credentialNumber;
    private long totalVerificationCount;
    private long verifiedVerificationCount;
}