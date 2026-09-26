package es.upm.miw.apaw.domain.model.notifications;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String eventType;

    @NotBlank
    private String subjectTemplate;

    @NotBlank
    private String bodyTemplate;

    @NotNull
    private Channel channel;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.channel == null) {
            this.channel = Channel.EMAIL;
        }
    }
}
