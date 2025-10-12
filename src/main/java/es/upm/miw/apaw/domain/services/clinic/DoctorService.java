package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.models.clinic.Doctor; // Importamos el Modelo de Dominio
import es.upm.miw.apaw.domain.persistenceports.clinic.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorPersistence doctorPersistence;

    @Autowired
    public DoctorService(DoctorPersistence doctorPersistence) {
        this.doctorPersistence = doctorPersistence;
    }

    // Cambiamos el tipo de retorno de List<DoctorDto> a List<Doctor>
    public List<Doctor> readAll() {
        // No hay mapeo a DTO, solo convertimos el Stream<Doctor> a List<Doctor>
        return this.doctorPersistence.readAll()
                .collect(Collectors.toList());
    }
}