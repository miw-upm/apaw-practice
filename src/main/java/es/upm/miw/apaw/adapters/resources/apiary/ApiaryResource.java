package es.upm.miw.apaw.adapters.resources.apiary;

import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.services.apiary.ApiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping(ApiaryResource.APIARIES)

public class ApiaryResource {
    public static final String APIARIES = "/apiary/apiaries";

    private final ApiaryService apiaryService;

    @Autowired
    public ApiaryResource(ApiaryService apiaryService) {
        this.apiaryService = apiaryService;
    }

    @GetMapping
    public Stream<Apiary> findByLocation(@RequestParam String location) {
        return this.apiaryService.findByLocation(location);
    }
}
