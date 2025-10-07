package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.services.videogame.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(LikeListResource.LIKE_LISTS)
public class LikeListResource {
    public static final String LIKE_LISTS = "/videogame/likeList";
    public static final String ID_ID = "/{id}";
    public static final String SHARED = "/shared";
    private final LikeListService likeListService;

    @Autowired
    public LikeListResource(LikeListService likeListService) {
        this.likeListService = likeListService;
    }

    @GetMapping(ID_ID+SHARED)
    public Boolean readSharedById(@PathVariable UUID id){
        return this.likeListService.readSharedById(id);
    }
}
