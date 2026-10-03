package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentLocationEntity;
import es.upm.miw.apaw.adapters.out.appointment.postgres.AppointmentLocationRepository;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(2)
@RequiredArgsConstructor
public class AppointmentLocationSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "cccccccc-dddd-eeee-ffff-aaaabbbb";
    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final AppointmentLocation LOCATION_0 = AppointmentLocation.builder()
            .id(ID_0)
            .name("Sala de Reuniones A")
            .address("Calle Mayor 10")
            .city("Madrid")
            .postalCode("28001")
            .room("101")
            .floor(1)
            .creationDate(LocalDateTime.of(2025, 1, 10, 9, 0))
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final AppointmentLocation LOCATION_1 = AppointmentLocation.builder()
            .id(ID_1)
            .name("Sala de Conferencias B")
            .address("Gran Via 100")
            .city("Barcelona")
            .postalCode("08008")
            .room("302")
            .floor(3)
            .creationDate(LocalDateTime.of(2025, 2, 15, 10, 0))
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final AppointmentLocation LOCATION_2 = AppointmentLocation.builder()
            .id(ID_2)
            .name("Oficina Central")
            .address("Paseo de la Castellana 200")
            .city("Madrid")
            .postalCode("28046")
            .floor(10)
            .creationDate(LocalDateTime.of(2025, 3, 20, 8, 30))
            .build();

    private final AppointmentLocationRepository appointmentLocationRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load AppointmentLocations -----------");
        this.seedAppointmentLocations();
    }

    private void seedAppointmentLocations() {
        List<AppointmentLocationEntity> locations = List.of(LOCATION_0, LOCATION_1, LOCATION_2).stream()
                .filter(location -> !this.appointmentLocationRepository.existsById(location.getId()))
                .map(AppointmentLocationEntity::new)
                .toList();
        this.appointmentLocationRepository.saveAll(locations);
        log.warn("        ------- appointment locations: {} added", locations.size());
    }
}
