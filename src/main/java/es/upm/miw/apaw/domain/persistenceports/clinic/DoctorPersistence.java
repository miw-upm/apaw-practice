package es.upm.miw.apaw.domain.persistenceports.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor;

import java.util.List;
import java.util.Optional;

// DoctorPersistence debe ser una INTERFACE (Port)
public interface DoctorPersistence {

    // Guarda un nuevo Doctor (CREATE)
    Doctor create(Doctor doctor);

    // Lee un Doctor por su número de licencia (READ by ID/Unique field)
    Optional<Doctor> readByLicenseNumber(Long licenseNumber);

    // Lee todos los Doctores (READ ALL)
    List<Doctor> readAll();

    // Actualiza un Doctor (UPDATE - Usado para PUT)
    Doctor update(Doctor doctor);

    // Elimina un Doctor por su número de licencia (DELETE)
    void delete(Long licenseNumber);
}
