package es.upm.miw.apaw.domain.model.credentials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialFindCriteria {

    private CredentialType credentialType;

    private Boolean expired;

    private VerificationStatus verificationStatus;

    private String userEmail;

    public boolean isAll() {
        return !this.hasCredentialType()
                && !this.hasExpired()
                && !this.hasVerificationStatus()
                && !this.hasUserEmail();
    }

    public boolean hasCredentialType() {
        return this.credentialType != null;
    }

    public boolean hasExpired() {
        return this.expired != null;
    }

    public boolean hasVerificationStatus() {
        return this.verificationStatus != null;
    }

    public boolean hasUserEmail() {
        return this.userEmail != null && !this.userEmail.isBlank();
    }
}