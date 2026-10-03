package es.upm.miw.apaw.adapters.in.notifications;

import es.upm.miw.apaw.domain.model.notifications.CreationNotification;
import es.upm.miw.apaw.domain.model.notifications.Notification;
import es.upm.miw.apaw.domain.model.notifications.NotificationFindCriteria;
import es.upm.miw.apaw.domain.model.notifications.NotificationTemplateFailureReport;
import es.upm.miw.apaw.domain.services.notifications.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(NotificationResource.NOTIFICATIONS)
@RequiredArgsConstructor
public class NotificationResource {
    public static final String NOTIFICATIONS = "/notifications";
    public static final String TEMPLATE_FAILURE_REPORT = "/report/template-failures";

    private final NotificationService notificationService;

    @GetMapping
    public List<Notification> find(@ModelAttribute NotificationFindCriteria criteria) {
        return this.notificationService.find(criteria);
    }

    @GetMapping(TEMPLATE_FAILURE_REPORT)
    public List<NotificationTemplateFailureReport> findTemplateFailureReport() {
        return this.notificationService.findTemplateFailureReport();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@Valid @RequestBody CreationNotification creation) {
        return this.notificationService.create(creation);
    }
}
