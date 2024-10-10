package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import es.upm.miw.apaw_practice.domain.services.wushu_sport.WushuSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WushuSchoolResource.WUSHU_SCHOOL)
public class WushuSchoolResource {

    static final String WUSHU_SCHOOL = "/wushu/wushu-school";

    static final String NAME_ID = "/{name}";
    static final String COMPETITORS_ITEMS ="/competitors";

    private WushuSchoolService wushuSchoolService;

    @Autowired
    public WushuSchoolResource(WushuSchoolService wushuSchoolService){
        this.wushuSchoolService = wushuSchoolService;
    }

    @PutMapping(NAME_ID + COMPETITORS_ITEMS)
    public WushuSchool updateWushuSchool(@PathVariable String name, @RequestBody List<Competitor> competitorList){
        return this.wushuSchoolService.updateWushuSchool(name,competitorList);
    }
}
