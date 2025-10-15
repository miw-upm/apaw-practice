package es.upm.miw.apaw.adapters.resources.airport;


import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.models.airport.PlaneSeatCountUpdating;
import es.upm.miw.apaw.domain.services.airport.PlaneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PlaneResource.PLANES)
public class PlaneResource {
    public static final String PLANES = "airport/planes";
    private final PlaneService planeService;

    @Autowired
    public PlaneResource(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping
    public Plane create(@Valid @RequestBody Plane plane) {
        return this.planeService.create(plane);
    }

    @PatchMapping
    public void update(@RequestBody List<PlaneSeatCountUpdating> planeSeatCountUpdatingList) {
        this.planeService.updateSeatCount(planeSeatCountUpdatingList.stream());
    }
}
