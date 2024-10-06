package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.military.Mission;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.MissionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    private final MissionPersistence missionPersistence;
    @Autowired
    public MissionService(MissionPersistence missionPersistence) { this.missionPersistence = missionPersistence; }

    public Mission update(String codeName, Mission mission) {
        return this.missionPersistence.update(codeName, mission);
    }
}
