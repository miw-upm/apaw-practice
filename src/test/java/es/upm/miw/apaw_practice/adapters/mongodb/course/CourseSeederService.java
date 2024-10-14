package es.upm.miw.apaw_practice.adapters.mongodb.course;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.TutoringSessionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.UserCourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.VideoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.TutoringSessionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.UserEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.VideoEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

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
        LogManager.getLogger(this.getClass()).warn("------- Course Initial Load -----------");

        VideoEntity[] videosCursoPython1 = {
                new VideoEntity("Introducción a Python", LocalTime.of(0, 15), LocalDateTime.of(2023, 1, 10, 10, 0)),
                new VideoEntity("Variables en Python", LocalTime.of(0, 20), LocalDateTime.of(2023, 1, 12, 11, 0)),
                new VideoEntity("Estructuras de control en Python", LocalTime.of(0, 30), LocalDateTime.of(2023, 1, 14, 12, 0)),
                new VideoEntity("Funciones en Python", LocalTime.of(0, 25), LocalDateTime.of(2023, 1, 16, 13, 0))
        };

        VideoEntity[] videosCursoPython2 = {
                new VideoEntity("POO en Python", LocalTime.of(0, 25), LocalDateTime.of(2023, 2, 10, 10, 0)),
                new VideoEntity("Herencia en Python", LocalTime.of(0, 35), LocalDateTime.of(2023, 2, 12, 11, 0)),
                new VideoEntity("Polimorfismo en Python", LocalTime.of(0, 45), LocalDateTime.of(2023, 2, 14, 12, 0)),
                new VideoEntity("Decoradores en Python", LocalTime.of(0, 40), LocalDateTime.of(2023, 2, 16, 13, 0))
        };

        VideoEntity[] videosCursoJava = {
                new VideoEntity("Introducción a Java", LocalTime.of(0, 35), LocalDateTime.of(2023, 1, 16, 14, 0)),
                new VideoEntity("Variables en Java", LocalTime.of(0, 25), LocalDateTime.of(2023, 1, 18, 15, 0)),
                new VideoEntity("POO en Java", LocalTime.of(0, 45), LocalDateTime.of(2023, 1, 20, 16, 0)),
                new VideoEntity("Manejo de Excepciones en Java", LocalTime.of(0, 40), LocalDateTime.of(2023, 1, 22, 17, 0))
        };

        VideoEntity[] videosCursoC = {
                new VideoEntity("Introducción al Lenguaje C", LocalTime.of(0, 30), LocalDateTime.of(2023, 1, 14, 12, 0)),
                new VideoEntity("Estructuras de control en C", LocalTime.of(0, 35), LocalDateTime.of(2023, 1, 16, 13, 0)),
                new VideoEntity("Funciones en C", LocalTime.of(0, 40), LocalDateTime.of(2023, 1, 18, 14, 0)),
                new VideoEntity("Punteros en C", LocalTime.of(0, 45), LocalDateTime.of(2023, 1, 20, 15, 0))
        };

        VideoEntity[] videosCursoSpring = {
                new VideoEntity("Spring Framework Básico", LocalTime.of(0, 40), LocalDateTime.of(2023, 1, 18, 16, 0)),
                new VideoEntity("Spring Boot", LocalTime.of(0, 50), LocalDateTime.of(2023, 1, 20, 17, 0)),
                new VideoEntity("Inyección de Dependencias", LocalTime.of(0, 45), LocalDateTime.of(2023, 1, 22, 18, 0)),
                new VideoEntity("Spring Data JPA", LocalTime.of(0, 55), LocalDateTime.of(2023, 1, 24, 19, 0))
        };

        this.videoRepository.saveAll(Arrays.asList(videosCursoPython1));
        this.videoRepository.saveAll(Arrays.asList(videosCursoPython2));
        this.videoRepository.saveAll(Arrays.asList(videosCursoJava));
        this.videoRepository.saveAll(Arrays.asList(videosCursoC));
        this.videoRepository.saveAll(Arrays.asList(videosCursoSpring));

        UserEntity[] usersPython1 = {
                new UserEntity("Jose", "jose@gmail.com", UserEntity.TypeUser.STUDENT),
                new UserEntity("Ana", "ana@gmail.com", UserEntity.TypeUser.STUDENT)
        };

        UserEntity[] usersPython2 = {
                new UserEntity("Luis", "luis@gmail.com", UserEntity.TypeUser.STUDENT),
                new UserEntity("Maria", "maria@gmail.com", UserEntity.TypeUser.STUDENT)
        };

        UserEntity[] usersJava = {
                new UserEntity("Laura", "laura@gmail.com", UserEntity.TypeUser.STUDENT),
                new UserEntity("Carlos", "carlos@gmail.com", UserEntity.TypeUser.STUDENT)
        };

        UserEntity[] usersC = {
                new UserEntity("Pedro", "pedro@gmail.com", UserEntity.TypeUser.STUDENT),
                new UserEntity("Miguel", "miguel@gmail.com", UserEntity.TypeUser.STUDENT)
        };

        UserEntity[] usersSpring = {
                new UserEntity("Sofia", "sofia@gmail.com", UserEntity.TypeUser.STUDENT),
                new UserEntity("Raul", "raul@gmail.com", UserEntity.TypeUser.STUDENT)
        };

        this.userRepository.saveAll(Arrays.asList(usersPython1));
        this.userRepository.saveAll(Arrays.asList(usersPython2));
        this.userRepository.saveAll(Arrays.asList(usersJava));
        this.userRepository.saveAll(Arrays.asList(usersC));
        this.userRepository.saveAll(Arrays.asList(usersSpring));

        TutoringSessionEntity[] tutoringSessionsCursoC = {
                new TutoringSessionEntity("Lenguaje C Básico", LocalDateTime.of(2024, 12, 5, 16, 0), BigDecimal.valueOf(20.00)),
                new TutoringSessionEntity("Punteros en C", LocalDateTime.of(2024, 12, 7, 16, 0), BigDecimal.valueOf(30.00))
        };

        TutoringSessionEntity[] tutoringSessionsCursoSpring = {
                new TutoringSessionEntity("Spring Framework Básico", LocalDateTime.of(2024, 12, 15, 18, 0), BigDecimal.valueOf(40.00)),
                new TutoringSessionEntity("Spring Boot Avanzado", LocalDateTime.of(2024, 12, 17, 18, 0), BigDecimal.valueOf(50.00))
        };

        this.tutoringSessionRepository.saveAll(Arrays.asList(tutoringSessionsCursoC));
        this.tutoringSessionRepository.saveAll(Arrays.asList(tutoringSessionsCursoSpring));

        CourseEntity[] courseEntities = {
                new CourseEntity("Curso de Introducción a Python", false, LocalDate.of(2023, 12, 10), LocalDate.of(2024, 06, 10), null, Arrays.asList(usersPython1), Arrays.asList(videosCursoPython1)),
                new CourseEntity("Curso de POO en Python", false, LocalDate.of(2023, 12, 15), LocalDate.of(2024, 06, 15), null, Arrays.asList(usersPython2), Arrays.asList(videosCursoPython2)),
                new CourseEntity("Curso de Java Básico", false, LocalDate.of(2023, 12, 25), LocalDate.of(2024, 06, 25), null, Arrays.asList(usersJava), Arrays.asList(videosCursoJava)),
                new CourseEntity("Curso de Lenguaje C", false, LocalDate.of(2023, 12, 20), LocalDate.of(2024, 06, 20), Arrays.asList(tutoringSessionsCursoC), Arrays.asList(usersC), Arrays.asList(videosCursoC)),
                new CourseEntity("Curso de Spring Framework", false, LocalDate.of(2023, 12, 30), LocalDate.of(2024, 06, 30), Arrays.asList(tutoringSessionsCursoSpring), Arrays.asList(usersSpring), Arrays.asList(videosCursoSpring))
        };

        this.courseRepository.saveAll(Arrays.asList(courseEntities));
    }

    public void deleteAll(){
        this.videoRepository.deleteAll();
        this.userRepository.deleteAll();
        this.courseRepository.deleteAll();
        this.tutoringSessionRepository.deleteAll();
    }

}
