package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorPersistenceMongodb(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor update(String dni, Doctor doctor) {

        DoctorEntity doctorEntity = mapToEntity(dni, doctor);
        DoctorEntity updatedDoctorEntity = doctorRepository.save(doctorEntity);

        return mapToDomain(updatedDoctorEntity);
    }

    private DoctorEntity mapToEntity(String dni, Doctor doctor) {
        return new DoctorEntity(dni, doctor.getFullname(), doctor.getSalary());
    }

    private Doctor mapToDomain(DoctorEntity doctorEntity) {
        return new Doctor(doctorEntity.getDni(), doctorEntity.getFullname(), doctorEntity.getSalary());
    }

    @Override
    public boolean existsByDni(String dni) {
        return doctorRepository.existsByDni(dni);
    }
}
