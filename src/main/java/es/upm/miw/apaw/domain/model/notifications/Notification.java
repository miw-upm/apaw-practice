package es.upm.miw.apaw.domain.model.notifications;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String message;

    @NotNull
    private LocalDate createdAt;

    private LocalDate sentAt;

    @NotNull
    private NotificationTemplate notificationTemplate;

    @NotNull
    private Priority priority;

    @NotNull
    private NotificationStatus notificationStatus;

    @NotNull
    private UserSnapshot recipient;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDate.now();
        if (this.priority == null) {
            this.priority = Priority.MEDIUM;
        }
        if (this.notificationStatus == null) {
            this.notificationStatus = NotificationStatus.PENDING;
        }
    }
}
