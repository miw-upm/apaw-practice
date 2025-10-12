package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.DoctorRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DoctorEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.domain.persistenceports.clinic.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorPersistenceMongodb(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Implementación para el GET endpoint (#1216)
    @Override
    public Stream<Doctor> readAll() {
        return this.doctorRepository.findAll().stream()
                .map(DoctorEntity::toDoctor);
    }

    // Implementación para el POST endpoint (#1217)
    @Override
    public Doctor create(Doctor doctor) {

        return this.doctorRepository.save(new DoctorEntity(doctor)).toDoctor();
    }

    // Implementación de Lectura por Clave de Negocio (Necesario para PUT/DELETE)
    @Override
    public Doctor readByLicenseNumber(Long licenseNumber) {

        return this.doctorRepository.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Doctor with license number: " + licenseNumber))
                .toDoctor();
    }

    // Implementación para el PUT endpoint (#1218)
    @Override
    public Doctor update(Doctor doctor) {
        // Guarda la Entidad y convierte el resultado de vuelta a Modelo.
        return this.doctorRepository.save(new DoctorEntity(doctor)).toDoctor();
    }

    // Implementación para el DELETE endpoint (#1219)
    @Override
    public void deleteByLicenseNumber(Long licenseNumber) {
        this.doctorRepository.findByLicenseNumber(licenseNumber)
                .ifPresent(this.doctorRepository::delete);
    }
}