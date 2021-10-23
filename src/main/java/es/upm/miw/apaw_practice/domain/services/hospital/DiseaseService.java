package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import es.upm.miw.apaw_practice.domain.models.hospital.DiseaseUpdate;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DiseasePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.HospitalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class DiseaseService {

    private final DiseasePersistence diseasePersistence;
    private final HospitalPersistence hospitalPersistence;

    @Autowired
    public DiseaseService(DiseasePersistence diseasePersistence, HospitalPersistence hospitalPersistence) {
        this.diseasePersistence = diseasePersistence;
        this.hospitalPersistence = hospitalPersistence;
    }

    public void updateDiseases(List<DiseaseUpdate> diseaseUpdates) {
        diseaseUpdates.forEach(diseaseUpdate ->
                this.diseasePersistence.updateDescription(diseaseUpdate.getAlias(), diseaseUpdate.getDescription())
        );
    }

    public Stream<Disease> findAliasByDoctorNick(String nick) {
        return this.hospitalPersistence.findByAvailableRoomsGreaterThan(10)
                .filter(hospital -> hospital.getPatients() != null)
                .flatMap(hospital -> hospital.getPatients().stream())
                .distinct()
                .filter(patient -> patient.getDoctor().getNick().equals(nick))
                .flatMap(patient -> patient.getDiseases().stream())
                .map(disease -> new Disease(null, null, disease.getAlias()));
    }
}
