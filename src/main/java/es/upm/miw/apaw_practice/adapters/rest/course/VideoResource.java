package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.domain.models.course.Course;
import es.upm.miw.apaw_practice.domain.models.course.Video;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.services.course.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VideoResource.VIDEOS)
public class VideoResource {

    static final String VIDEOS = "/course/video";
    static final String COURSE = "/course";

    private final VideoService videoService;

    @Autowired
    public VideoResource(VideoService videoService) {
        this.videoService = videoService;
    }

    @PutMapping(VIDEOS)
    public Video update(@PathVariable String name, @RequestBody Video video){
        return this.videoService.update(name, video);
    }

    @PatchMapping(COURSE)
    public Video updateCourse(@PathVariable String name, @RequestParam String tittleCourse){
        return this.videoService.update(name, tittleCourse);
    }
}
