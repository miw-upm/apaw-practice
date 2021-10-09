package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Album;
import es.upm.miw.apaw_practice.domain.services.music_manager.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AlbumResource.ALBUMS)
public class AlbumResource {
    static final String ALBUMS = "/music_manager/albums";
    static final String ID_ID = "/{id}";

    AlbumService albumService;

    @Autowired
    public AlbumResource(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(ID_ID)
    public Album getAlbum(@PathVariable String id) {
        return this.albumService.read(id);
    }
}