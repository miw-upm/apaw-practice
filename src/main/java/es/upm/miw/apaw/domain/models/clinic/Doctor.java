package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Doctor {
    private UUID doctorId;
    private Long licenseNumber;
    private Boolean isActive;

    private UUID userId;

   public Doctor(Long licenseNumber, Boolean isActive, UUID userId) {
        this.doctorId = UUID.randomUUID();
        this.licenseNumber = licenseNumber;
        this.isActive = isActive;
        this.userId = userId;
    }
}
