package es.upm.miw.apaw.adapters.resources.airport;


import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.services.airport.PlaneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
