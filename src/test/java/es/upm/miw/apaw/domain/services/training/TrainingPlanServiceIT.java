package es.upm.miw.apaw.domain.services.training;

import es.upm.miw.apaw.adapters.out.training.postgres.TrainingPlanEntity;
import es.upm.miw.apaw.adapters.out.training.postgres.TrainingPlanRepository;
import es.upm.miw.apaw.domain.model.training.TrainingPlanFindCriteria;
import es.upm.miw.apaw.adapters.out.training.postgres.CourseEntity;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.training.Course;
import es.upm.miw.apaw.domain.model.training.CreationTrainingPlan;
import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.TrainingSeederForDev.COURSE_ID_1;
import static es.upm.miw.apaw.config.seeders.TrainingSeederForDev.COURSE_ID_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class TrainingPlanServiceIT {

    @Autowired
    private TrainingPlanService trainingPlanService;
    
    @Autowired
    private TrainingPlanRepository trainingPlanRepository;
    
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
                
        CreationTrainingPlan creation = new CreationTrainingPlan(
                "plan-" + UUID.randomUUID().toString(),
                LocalDate.now().plusMonths(2),
                new BigDecimal("8.5"),
                List.of(COURSE_ID_1, COURSE_ID_2),
                List.of(user.getId())
        );
        
        when(this.userFinder.findByIds(any())).thenReturn(List.of(user));

        TrainingPlan trainingPlan = this.trainingPlanService.create(creation);

        assertThat(trainingPlan.getId()).isNotNull();
        assertThat(trainingPlan.getApprovalDate()).isEqualTo(LocalDate.now());
        assertThat(trainingPlan.getEndDate()).isEqualTo(creation.getEndDate());
        assertThat(trainingPlan.getCourses()).extracting(Course::getId).containsExactly(COURSE_ID_1, COURSE_ID_2);
        assertThat(trainingPlan.getUserSnapshots()).extracting(UserSnapshot::getId).containsExactly(user.getId());
        
        TrainingPlanEntity entity = this.trainingPlanRepository.findById(trainingPlan.getId()).orElseThrow();
        assertThat(entity.getPlanCode()).isEqualTo(creation.getPlanCode());
        assertThat(entity.getCourses()).extracting(CourseEntity::getId).containsExactlyInAnyOrder(COURSE_ID_1, COURSE_ID_2);
        assertThat(entity.getUserIds()).containsExactly(user.getId());
    }
    
    @Test
    @Transactional
    void testFindWithCriteria() {
        UserSnapshot mockUser = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .firstName("MockName")
                .build();
        when(this.userFinder.findByIds(any())).thenReturn(List.of(mockUser));

        TrainingPlanFindCriteria criteria = new TrainingPlanFindCriteria();
        criteria.setCourseName("Curso de prueba");
        criteria.setUserFirstName("MockName");

        List<TrainingPlan> result = this.trainingPlanService.find(criteria);
        
        assertThat(result).isEmpty();
    }
}
