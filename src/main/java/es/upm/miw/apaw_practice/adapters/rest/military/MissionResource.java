package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.domain.models.military.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MissionResource.MISSIONS)
public class MissionResource {
    static final String MISSIONS = "/military/missions";
    static final String CODENAME_ID = "/{codeName}";

    @Autowired
    public MissionResource() {
        // empty for now
    }

    @PutMapping(CODENAME_ID)
    public Mission update(@PathVariable String codeName, @RequestBody Mission mission) {
        return new Mission(codeName, mission.getInternational(), mission.getStartDate(), mission.getUnit(), mission.getWeapons());
    }

}
