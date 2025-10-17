package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Doctor;
import es.upm.miw.apaw.domain.persistenceports.clinic.DoctorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    private final DoctorPersistence doctorPersistence;

    @Autowired
    public DoctorService(DoctorPersistence doctorPersistence) {
        this.doctorPersistence = doctorPersistence;
    }

    public Doctor create(Doctor doctor) {
        this.assertLicenseNumberNotExist(doctor.getLicenseNumber());
        return this.doctorPersistence.create(doctor);
    }

    public void assertLicenseNumberNotExist(Long licenseNumber) {
        if (this.doctorPersistence.readByLicenseNumber(licenseNumber).isPresent()) {
            throw new ConflictException("Doctor license number already exists: " + licenseNumber);
        }
    }

    public Doctor update(Long licenseNumber, Doctor doctor) {
        if (!licenseNumber.equals(doctor.getLicenseNumber())) {
            throw new ConflictException("License number from path (" + licenseNumber +
                    ") does not match license number in body (" + doctor.getLicenseNumber() + ").");
        }
        this.doctorPersistence.readByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Doctor license number not found: " + licenseNumber));
        return this.doctorPersistence.update(doctor);
    }

    public List<Doctor> readAll() {
        return this.doctorPersistence.readAll();
    }

    public void delete(Long licenseNumber) {
        this.doctorPersistence.readByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Doctor license number to delete not found: " + licenseNumber));
        this.doctorPersistence.delete(licenseNumber);
    }

    public Doctor readByLicenseNumber(Long licenseNumber) {
        return this.doctorPersistence.readByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Doctor license number not found: " + licenseNumber));
    }

    // PATCH implementation
    public Doctor patch(Long licenseNumber, Map<String, Object> updates) {
        Doctor doctor = this.doctorPersistence.readByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Doctor license number not found: " + licenseNumber));

        if (updates.containsKey("specialty")) {
            doctor.setSpecialty((String) updates.get("specialty"));
        }
        if (updates.containsKey("name")) {
            doctor.setName((String) updates.get("name"));
        }
        // Add more fields if needed

        return this.doctorPersistence.update(doctor);
    }
}