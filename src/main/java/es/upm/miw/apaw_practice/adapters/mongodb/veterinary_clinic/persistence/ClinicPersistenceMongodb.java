package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.ClinicRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.ClinicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("clinicPersistence")
public class ClinicPersistenceMongodb implements ClinicPersistence {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicPersistenceMongodb(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Clinic readByName(String name) {
        return this.clinicRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(" Clinic name? " + name))
                .toClinic();
    }

    @Override
    public void delete(String name) {
        this.clinicRepository.deleteByName(name);
    }
}