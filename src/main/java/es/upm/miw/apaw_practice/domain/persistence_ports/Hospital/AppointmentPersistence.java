package es.upm.miw.apaw_practice.domain.persistence_ports.Hospita.AppoinmentPersistencel;

import java.util.List;
import java.util.Optional;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;


public interface AppoinmentPersistence {
    List<Appointment> findAll();

}
