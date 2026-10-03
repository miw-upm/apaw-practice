package es.upm.miw.apaw.domain.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationFindCriteria {

    private Priority priority;

    private Boolean sent;

    private String eventType;

    private String recipientEmail;

    public boolean isAll() {
        return !this.hasPriority() && !this.hasSent() && !this.hasEventType() && !this.hasRecipientEmail();
    }

    public boolean hasPriority() {
        return this.priority != null;
    }

    public boolean hasSent() {
        return this.sent != null;
    }

    public boolean hasEventType() {
        return this.eventType != null && !this.eventType.isBlank();
    }

    public boolean hasRecipientEmail() {
        return this.recipientEmail != null && !this.recipientEmail.isBlank();
    }
}
