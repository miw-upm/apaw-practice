package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.car_workshop.OBDFault;
import es.upm.miw.apaw_practice.domain.services.car_workshop.OBDFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping(OBDFaultResource.OBDFAULTS)
public class OBDFaultResource {

    static final String OBDFAULTS = "/car-workshop/obd-faults";

    static final String ISNOTITVSAFE = "/cannot-pass-itv";

    static final String CODE = "/{code}";

    static final String CODES = "/codes";

    static final String SEARCHBYCARCOMPONENTNAME = "/search-by-car-component";

    private final OBDFaultService obdFaultService;

    @Autowired
    public OBDFaultResource(OBDFaultService obdFaultService) {
        this.obdFaultService = obdFaultService;
    }

    @GetMapping(ISNOTITVSAFE)
    public Stream<OBDFault> findByIsNotITVSafe() {
        return this.obdFaultService.findByIsITVSafe(false);
    }

    @PatchMapping(CODE)
    public OBDFault updatePartial(@PathVariable String code, @RequestBody OBDFault obdFault) {
        return this.obdFaultService.updatePartial(code,obdFault);
    }

    @GetMapping(CODE + SEARCHBYCARCOMPONENTNAME)
    public Stream<String> findByCarComponentName(@RequestParam String q) { //name:{carComponentName}
        String carComponentName = new LexicalAnalyzer().extractWithAssure(q, "name");
        return this.obdFaultService.findByCarComponentName(carComponentName);
    }
}
