package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.services.tennis_courts.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(CourtResource.COURTS)
public class CourtResource {

    public static final String COURTS = "/courts";
    public static final String NUMBER = "/{number}";
    public static final String EQUIPMENTS = "/equipments";
    public static final String SUM = "/sum";

    private CourtService courtService;

    @Autowired
    public CourtResource(CourtService courtService){
        this.courtService = courtService;
    }

    @GetMapping(CourtResource.NUMBER + CourtResource.EQUIPMENTS + CourtResource.SUM)
    public BigDecimal get(@PathVariable int number){
        return this.courtService.get(number);
    }

}
