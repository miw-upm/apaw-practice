package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.VideoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.VideoEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.course.Video;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.VideoPersistence;
import org.springframework.stereotype.Repository;

@Repository("videoPersistence")
public class VideoPersistenceMongodb implements VideoPersistence {

    private final VideoRepository videoRepository;
    private final CourseRepository courseRepository;

    public VideoPersistenceMongodb(VideoRepository videoRepository, CourseRepository courseRepository) {
        this.videoRepository = videoRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Video update(String name, Video video) {

        VideoEntity videoEntity = this.videoRepository.findByName(name)
                .orElseThrow( () -> new NotFoundException("Video name: " + name));

        videoEntity.setName(video.getName());
        videoEntity.setDuration(video.getDuration());
        videoEntity.setCreationDate(video.getCreationDate());

        Video videoResult = this.videoRepository.save(videoEntity).toVideo();

        if (video.getCourse() != null) {
            String tittleVideo = video.getCourse().getTitle();
            CourseEntity courseEntity = this.courseRepository.findByTitle(video.getCourse().getTitle())
                    .orElseThrow(() -> new NotFoundException("Course name: " + tittleVideo));

            videoResult.setCourse(courseEntity.toCourse());
        } else {
            videoResult.setCourse(null);
        }
        return videoResult;
    }

    @Override
    public Video update(String name, String titleCourse) {
        VideoEntity videoEntity = this.videoRepository.findByName(name)
                .orElseThrow( () -> new NotFoundException("Video name: " + name));

        CourseEntity courseEntity = this.courseRepository.findByTitle(titleCourse)
                .orElseThrow( () -> new NotFoundException("Course tittle: " + titleCourse));

        Video video = videoEntity.toVideo();
        video.setCourse(courseEntity.toCourse());

        courseEntity.getVideos().remove(videoEntity);
        this.courseRepository.save(courseEntity);

        return video;
    }
}
