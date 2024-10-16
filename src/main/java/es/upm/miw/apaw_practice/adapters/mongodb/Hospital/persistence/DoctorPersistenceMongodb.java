package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorPersistenceMongodb(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor update(String dni, Doctor doctor) {
        // Verificar si el doctor existe en la base de datos
        DoctorEntity existingDoctorEntity = doctorRepository.findById(dni)
                .orElseThrow(() -> new NotFoundException("Doctor with dni '" + dni + "' not found."));

        // Actualizar los campos del doctor existente con los valores nuevos
        existingDoctorEntity.setFullname(doctor.getFullname());
        existingDoctorEntity.setSalary(doctor.getSalary());

        // Guardar los cambios en MongoDB
        DoctorEntity updatedDoctorEntity = doctorRepository.save(existingDoctorEntity);

        // Retornar el objeto de dominio actualizado
        return new Doctor(updatedDoctorEntity.getDni(), updatedDoctorEntity.getFullname(), updatedDoctorEntity.getSalary());
    }
}
