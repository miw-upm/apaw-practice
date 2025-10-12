package es.upm.miw.apaw.adapters.resources.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.services.videoWebsite.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
