package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.DoctorRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DoctorEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.domain.persistenceports.clinic.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorPersistenceMongodb(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor create(Doctor doctor) {
        return this.doctorRepository.save(new DoctorEntity(doctor)).toDoctor();
    }

    @Override
    public List<Doctor> readAll() {
        return this.doctorRepository.findAll().stream()
                .map(DoctorEntity::toDoctor)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Doctor> readByLicenseNumber(Long licenseNumber) {
        return this.doctorRepository.findByLicenseNumber(licenseNumber)
                .map(DoctorEntity::toDoctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        // La entidad debe existir para actualizar
        DoctorEntity entity = this.doctorRepository.findByLicenseNumber(doctor.getLicenseNumber())
                .orElseThrow(() -> new NotFoundException("Doctor license number to update not found: " + doctor.getLicenseNumber()));

        DoctorEntity updatedEntity = new DoctorEntity(doctor);
        // Mantiene el ID de MongoDB (String) para la actualización
        updatedEntity.setId(entity.getId());

        return this.doctorRepository.save(updatedEntity).toDoctor();
    }

    // Implementación del DELETE (#1219)
    @Override
    public void delete(Long licenseNumber) {
        // 1. Busca la entidad por su clave de negocio para asegurar que existe y obtener el ID de Mongo
        DoctorEntity doctorEntity = this.doctorRepository.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Doctor license number to delete not found: " + licenseNumber));

        // 2. Si existe, la elimina usando el ID de MongoDB
        this.doctorRepository.deleteById(doctorEntity.getId());
    }
}
