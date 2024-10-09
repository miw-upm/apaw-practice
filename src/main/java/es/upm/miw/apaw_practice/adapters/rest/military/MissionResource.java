package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.domain.models.military.Mission;
import es.upm.miw.apaw_practice.domain.services.military.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MissionResource.MISSIONS)
public class MissionResource {
    static final String MISSIONS = "/military/missions";
    static final String CODENAME_ID = "/{codeName}";

    private final MissionService missionService;

    @Autowired
    public MissionResource(MissionService missionService) {
        this.missionService = missionService;
    }

    @PutMapping(CODENAME_ID)
    public Mission update(@PathVariable String codeName, @RequestBody Mission mission) {
        return this.missionService.update(codeName, mission);
    }
}
