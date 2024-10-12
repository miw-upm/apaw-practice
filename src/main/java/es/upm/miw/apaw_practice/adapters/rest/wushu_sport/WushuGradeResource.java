package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.domain.services.wushu_sport.WushuGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WushuGradeResource.WUSHU_GRADES)
public class WushuGradeResource {

    static final String WUSHU_GRADES  = "/wushu/wushu-grades";

    static final String GRADE_TITLE_ID ="/{gradeTitle}";

    private final WushuGradeService wushuGradeService;

    @Autowired
    public WushuGradeResource( WushuGradeService wushuGradeService){
        this.wushuGradeService = wushuGradeService;
    }

    @DeleteMapping(GRADE_TITLE_ID)
    public void delete(@PathVariable String gradeTitle){
        this.wushuGradeService.delete(gradeTitle);
    }

    @PatchMapping(GRADE_TITLE_ID)
    public void updateGradeLevel(@PathVariable String gradeTitle, @RequestBody Integer gradeLevel){
        this.wushuGradeService.updateGradeLevel(gradeTitle,gradeLevel);
    }
}
