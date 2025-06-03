package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import es.upm.miw.apaw_practice.domain.services.music_festival.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StageResource.STAGES)
public class StageResource {

    static final String STAGES = "/music-festival/stages";
    static final String NAME_ID = "/{name}";

    private final StageService stageService;

    @Autowired
    public StageResource(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping
    public Stage create(@RequestBody Stage stage) {
        stage.doDefault();
        return this.stageService.create(stage);
    }

    @DeleteMapping(NAME_ID)
    public void delete(@PathVariable String name) {
        this.stageService.delete(name);
    }

}