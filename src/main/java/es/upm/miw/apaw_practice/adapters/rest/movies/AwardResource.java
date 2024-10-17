package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.services.movies.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AwardResource.AWARDS)
public class AwardResource {

    static final String AWARDS = "/movies/awards";
    static final String NAME_ID = "/{nameCategoryYear}";
    public static final String SEARCH = "/search";

    private final AwardService awardService;

    @Autowired
    public AwardResource(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping(SEARCH)
    public List<String> findAwardNamesByActorRealName(@RequestParam String realName) {
        return this.awardService.findAwardNamesByActorRealName(realName);
    }

    @DeleteMapping("/{nameCategoryYear}")
    public ResponseEntity<Void> deleteAward(@PathVariable String nameCategoryYear) {
        this.awardService.deleteAward(nameCategoryYear);
        return ResponseEntity.ok().build();
    }
}
