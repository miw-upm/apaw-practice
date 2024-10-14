package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppoinmentPersistence {

    // Obtener todas las citas médicas
    List<Appointment> findAll();
}
