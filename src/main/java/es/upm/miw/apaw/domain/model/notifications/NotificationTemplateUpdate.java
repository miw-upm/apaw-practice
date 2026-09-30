package es.upm.miw.apaw.domain.model.notifications;

public record NotificationTemplateUpdate(
        String eventType,
        String subjectTemplate,
        String bodyTemplate,
        Channel channel
) {
}
