package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.DiseaseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.PatientEntity;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.PatientPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("patientPersistence")
public class PatientPersistenceMongodb implements PatientPersistence{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientPersistenceMongodb(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void delete(String dni) {
        this.patientRepository.deleteByDni(dni);
    }

    @Override
    public Patient update(String dni, Patient patient) {
        PatientEntity patientEntity = this.patientRepository
                .findByDni(patient.getDni()).get();

        BeanUtils.copyProperties(patient, patientEntity, "id");
        return this.patientRepository.save(patientEntity).toPatient();
    }

    @Override
    public List<Patient> findAllWithDiseaseSeverity(Boolean severity) {
        return this.patientRepository.findAll().stream()
                .map(PatientEntity::toPatient)
                .filter(patient -> patient.getDiseases() != null)
                .filter(patient -> patient.getDiseases().stream()
                        .anyMatch(disease -> disease.getSevere() == severity)
                ).collect(Collectors.toList());
    }


}
