package es.upm.miw.apaw.adapters.in.notifications;

import es.upm.miw.apaw.domain.model.notifications.CreationNotification;
import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.services.notifications.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(NotificationResource.NOTIFICATIONS)
@RequiredArgsConstructor
public class NotificationResource {
    public static final String NOTIFICATIONS = "/notifications";

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@Valid @RequestBody CreationNotification creation) {
        return this.notificationService.create(creation);
    }
}
