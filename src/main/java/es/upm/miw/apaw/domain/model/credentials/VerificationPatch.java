package es.upm.miw.apaw.domain.model.credentials;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class VerificationPatch {

    private LocalDateTime verifiedAt;
    private String method;
    private String name;
    private String notes;
    private BigDecimal score;
    private VerificationStatus verificationStatus;

    private boolean verifiedAtPresent;
    private boolean methodPresent;
    private boolean namePresent;
    private boolean notesPresent;
    private boolean scorePresent;
    private boolean verificationStatusPresent;

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
        this.verifiedAtPresent = true;
    }

    public void setMethod(String method) {
        this.method = method;
        this.methodPresent = true;
    }

    public void setName(String name) {
        this.name = name;
        this.namePresent = true;
    }

    public void setNotes(String notes) {
        this.notes = notes;
        this.notesPresent = true;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
        this.scorePresent = true;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
        this.verificationStatusPresent = true;
    }
}