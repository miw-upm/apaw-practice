package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("doctorPersistence")
public class DoctorPersistenceMongodb implements DoctorPersistence {

    @Autowired
    private DoctorRepository doctorRepository; // Assuming there's a DoctorRepository interface

    @Override
    public Doctor create(Doctor doctor) {
        DoctorEntity doctorEntity = new DoctorEntity(doctor); // Assuming you have a DoctorEntity class
        return doctorRepository.save(doctorEntity).toModel(); // Assuming toModel method exists
    }

    @Override
    public Optional<Doctor> read(String id) {
        return doctorRepository.findById(id).map(DoctorEntity::toModel);
    }

    @Override
    public Doctor update(Doctor doctor) {
        // Check if the doctor exists before updating
        if (!doctorRepository.existsById(doctor.getId())) {
            throw new NotFoundException("Doctor not found with id: " + doctor.getId());
        }
        DoctorEntity doctorEntity = new DoctorEntity(doctor);
        return doctorRepository.save(doctorEntity).toModel();
    }

    @Override
    public void delete(String id) {
        // Check if the doctor exists before deleting
        if (!doctorRepository.existsById(id)) {
            throw new NotFoundException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> readAll() {
        return doctorRepository.findAll().stream()
                .map(DoctorEntity::toModel)
                .collect(Collectors.toList());
    }
}
