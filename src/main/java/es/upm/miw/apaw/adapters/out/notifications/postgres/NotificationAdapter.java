package es.upm.miw.apaw.adapters.out.notifications.postgres;

import es.upm.miw.apaw.domain.ports.out.notifications.NotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationGateway {
    private final NotificationRepository notificationRepository;
}
