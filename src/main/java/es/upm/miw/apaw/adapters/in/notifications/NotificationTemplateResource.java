package es.upm.miw.apaw.adapters.in.notifications;

import es.upm.miw.apaw.domain.model.notifications.NotificationTemplate;
import es.upm.miw.apaw.domain.services.notifications.NotificationTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(NotificationTemplateResource.NOTIFICATION_TEMPLATE)
@RequiredArgsConstructor
public class NotificationTemplateResource {
    public static final String NOTIFICATION_TEMPLATE = "/notification-template";
    public static final String ID = "/{id}";

    private final NotificationTemplateService notificationTemplateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationTemplate create(@Valid @RequestBody NotificationTemplate notificationTemplate) {
        return this.notificationTemplateService.create(notificationTemplate);
    }

    @GetMapping
    public List<NotificationTemplate> findAll() {
        return this.notificationTemplateService.findAll();
    }

    @GetMapping(ID)
    public NotificationTemplate read(@PathVariable UUID id) {
        return this.notificationTemplateService.read(id);
    }

    @PutMapping(ID)
    public NotificationTemplate update(@PathVariable UUID id, @Valid @RequestBody NotificationTemplate update) {
        return this.notificationTemplateService.update(id, update);
    }

    @PatchMapping(ID)
    public NotificationTemplate patch(@PathVariable UUID id, @RequestBody NotificationTemplate update) {
        return this.notificationTemplateService.patch(id, update);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.notificationTemplateService.delete(id);
    }
}
