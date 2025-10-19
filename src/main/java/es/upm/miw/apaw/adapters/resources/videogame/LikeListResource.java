package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.services.videogame.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(LikeListResource.LIKE_LISTS)
public class LikeListResource {
    public static final String LIKE_LISTS = "/videogame/likeLists";
    public static final String ID_ID = "/{id}";
    public static final String SHARED = "/shared";
    public static final String MOBILE = "/{mobile}";
    public static final String GAME_SECTOR = "/gamesLiked/sectors";
    private final LikeListService likeListService;

    @Autowired
    public LikeListResource(LikeListService likeListService) {
        this.likeListService = likeListService;
    }

    @GetMapping(ID_ID + SHARED)
    public Boolean readSharedById(@PathVariable UUID id) {
        return this.likeListService.readSharedById(id);
    }

    @GetMapping(MOBILE + GAME_SECTOR)
    public List<String> obtainSectorsByMobile(@PathVariable("mobile") String mobile) {
        return this.likeListService.obtainSectorsByMobile(mobile);
    }

}
