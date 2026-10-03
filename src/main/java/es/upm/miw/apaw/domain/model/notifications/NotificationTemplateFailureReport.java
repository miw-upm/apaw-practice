package es.upm.miw.apaw.domain.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplateFailureReport {
    private String eventType;
    private Channel channel;
    private long totalNotificationCount;
    private long failedNotificationCount;
    private double failureRate;
}
