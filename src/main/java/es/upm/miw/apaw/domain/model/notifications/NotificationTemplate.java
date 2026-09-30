package es.upm.miw.apaw.domain.model.notifications;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationTemplate {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    @Size(max = 60, message = "eventType must not exceed 60 characters")
    private String eventType;

    @NotBlank
    @Size(max = 60, message = "subjectTemplate must not exceed 60 characters")
    private String subjectTemplate;

    @NotBlank
    @Size(max = 500, message = "bodyTemplate must not exceed 500 characters")
    private String bodyTemplate;

    private Channel channel;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.channel == null) {
            this.channel = Channel.EMAIL;
        }
    }
}
