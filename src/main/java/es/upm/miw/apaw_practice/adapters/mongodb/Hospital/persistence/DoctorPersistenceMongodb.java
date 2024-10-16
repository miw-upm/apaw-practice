package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorPersistenceMongodb(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorEntity update(String dni, DoctorEntity updatedDoctor) {
        Optional<DoctorEntity> doctorOptional = this.doctorRepository.findById(dni);
        if (doctorOptional.isPresent()) {
            DoctorEntity existingDoctor = doctorOptional.get();
            existingDoctor.setFullname(updatedDoctor.getFullname());
            existingDoctor.setSalary(updatedDoctor.getSalary());
            // Actualiza otros campos según sea necesario
            return this.doctorRepository.save(existingDoctor);
        } else {
            throw new RuntimeException("Doctor not found with dni: " + dni);
        }
    }
}
