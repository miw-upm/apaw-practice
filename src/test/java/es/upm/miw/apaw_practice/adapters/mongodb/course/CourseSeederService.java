package es.upm.miw.apaw_practice.adapters.mongodb.course;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.TutoringSessionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.UserCourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.VideoRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseSeederService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserCourseRepository userRepository;
    @Autowired
    private TutoringSessionRepository tutoringSessionRepository;
    @Autowired
    private VideoRepository videoRepository;

    public void seedDatabase(){
        LogManager.getLogger(this.getClass()).warn("------- Bank Initial Load -----------");
    }

    public void deleteAll(){
        this.videoRepository.deleteAll();
        this.userRepository.deleteAll();
        this.courseRepository.deleteAll();
        this.tutoringSessionRepository.deleteAll();
    }

}
