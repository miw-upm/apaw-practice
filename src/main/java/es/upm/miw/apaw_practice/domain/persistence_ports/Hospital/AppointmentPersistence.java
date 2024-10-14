package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import java.util.List;
import java.util.Optional;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentPersistence { // Corregido aquí
    List<Appointment> findAll();
}
