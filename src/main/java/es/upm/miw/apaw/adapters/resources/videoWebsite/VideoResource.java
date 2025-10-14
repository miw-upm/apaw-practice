package es.upm.miw.apaw.adapters.resources.videoWebsite;

import es.upm.miw.apaw.domain.services.videoWebsite.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import es.upm.miw.apaw.domain.models.videoWebsite.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(VideoResource.VIDEOS)
public class VideoResource {
    public static final String VIDEOS = "/videoWebsite/videos";


    private final VideoService videoService;

    @Autowired
    public VideoResource (VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<Video> findByTitle(@RequestParam("title") String title) {
        return videoService.findByTitle(title).toList();
    }

    @PutMapping("/{id}")
    public Video updateVideo(@PathVariable UUID id, @Valid @RequestBody Video video) {
        return this.videoService.update(id, video);
    }
}
