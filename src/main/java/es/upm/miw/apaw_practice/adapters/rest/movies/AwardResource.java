package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.services.movies.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AwardResource.AWARDS)
public class AwardResource {

    static final String AWARDS = "/movies/awards";
    static final String NAME_ID = "/{nameCategoryYear}";

    private final AwardService awardService;

    @Autowired
    public AwardResource(AwardService awardService) {
        this.awardService = awardService;
    }

    @DeleteMapping("/{nameCategoryYear}")
    public ResponseEntity<Void> deleteAward(@PathVariable String nameCategoryYear) {
        this.awardService.deleteAward(nameCategoryYear);
        return ResponseEntity.ok().build();
    }
}
