package es.upm.miw.apaw.domain.persistenceports.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository
public interface DoctorPersistence {

    // 1. Método esencial para el GET endpoint (#1216)
    Stream<Doctor> readAll();

    // 2. Método esencial para el POST endpoint (#1217)
    Doctor create(Doctor doctor);

    // 3. Método necesario para obtener un Doctor por su clave de negocio (licenseNumber)
    Doctor readByLicenseNumber(Long licenseNumber);

    // 4. Método esencial para el PUT endpoint (#1218)
    Doctor update(Doctor doctor);

    // 5. Método esencial para el DELETE endpoint (#1219)
    void deleteByLicenseNumber(Long licenseNumber);


}