package es.upm.miw.apaw.adapters.out.notifications.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplateEntity, UUID> {
}
