package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionRepository;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class SurveySeederForDev implements ApplicationRunner {
    private static final String PREFIX = "bbbbbbbb-cccc-dddd-eeee-ffffffff";

    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final SurveyQuestion QUESTION_0 = SurveyQuestion.builder()
            .id(ID_0)
            .text("How satisfied are you?")
            .surveyQuestionType(SurveyQuestionType.RATING)
            .required(true)
            .maxLength(2)
            .options(List.of("1", "2", "3", "4", "5"))
            .build();

    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final SurveyQuestion QUESTION_1 = SurveyQuestion.builder()
            .id(ID_1)
            .text("What is your main goal?")
            .surveyQuestionType(SurveyQuestionType.TEXT)
            .required(true)
            .maxLength(80)
            .options(List.of("Career", "Study", "Travel", "Personal growth"))
            .build();

    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final SurveyQuestion QUESTION_2 = SurveyQuestion.builder()
            .id(ID_2)
            .text("Would you attend again?")
            .surveyQuestionType(SurveyQuestionType.YES_NO)
            .required(true)
            .maxLength(3)
            .options(List.of("Yes", "No"))
            .build();

    private static final String SURVEY_PREFIX = "cccccccc-dddd-eeee-ffff-aaaaaaaa";
    private static final String USER_PREFIX = "dddddddd-eeee-ffff-aaaa-bbbbbbbb";
    public static final UUID SURVEY_ID_0 = UUID.fromString(SURVEY_PREFIX + "0000");
    public static final Survey SURVEY_0 = Survey.builder()
            .id(SURVEY_ID_0)
            .title("Course satisfaction")
            .description("Feedback about the course experience")
            .createdDate(LocalDate.of(2025, 1, 10))
            .submittedDate(LocalDate.of(2025, 1, 20))
            .language("English")
            .surveyQuestions(List.of(QUESTION_0, QUESTION_2))
            .userSnapshot(user("0000", "600000200", "encuestado0", "Madrid"))
            .build();

    public static final UUID SURVEY_ID_1 = UUID.fromString(SURVEY_PREFIX + "0001");
    public static final Survey SURVEY_1 = Survey.builder()
            .id(SURVEY_ID_1)
            .title("Student goals")
            .description("Survey about the goals of the students")
            .createdDate(LocalDate.of(2025, 2, 5))
            .language("Spanish")
            .surveyQuestions(List.of(QUESTION_1))
            .userSnapshot(user("0001", "600000201", "encuestado1", "Sevilla"))
            .build();

    public static final UUID SURVEY_ID_2 = UUID.fromString(SURVEY_PREFIX + "0002");
    public static final Survey SURVEY_2 = Survey.builder()
            .id(SURVEY_ID_2)
            .title("General feedback")
            .description("General feedback about the whole program")
            .createdDate(LocalDate.of(2025, 3, 1))
            .language("English")
            .surveyQuestions(List.of(QUESTION_0, QUESTION_1, QUESTION_2))
            .userSnapshot(user("0002", "600000202", "encuestado2", "Madrid"))
            .build();

    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyRepository surveyRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName, String city) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .city(city)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedSurveyQuestions();
        this.seedSurveys();
    }

    private void seedSurveyQuestions() {
        List<SurveyQuestionEntity> surveyQuestions = List.of(QUESTION_0, QUESTION_1, QUESTION_2).stream()
                .filter(question -> !this.surveyQuestionRepository.existsById(question.getId()))
                .map(SurveyQuestionEntity::new)
                .toList();
        this.surveyQuestionRepository.saveAll(surveyQuestions);
        log.warn("        ------- survey questions: {} added", surveyQuestions.size());
    }

    private void seedSurveys() {
        List<SurveyEntity> surveys = List.of(SURVEY_0, SURVEY_1, SURVEY_2).stream()
                .filter(survey -> !this.surveyRepository.existsById(survey.getId()))
                .map(this::toEntity)
                .toList();
        this.surveyRepository.saveAll(surveys);
        log.warn("        ------- surveys: {} added", surveys.size());
    }

    private SurveyEntity toEntity(Survey survey) {
        SurveyEntity entity = new SurveyEntity(survey);
        entity.setSurveyQuestions(survey.getSurveyQuestions().stream()
                .map(question -> this.surveyQuestionRepository.getReferenceById(question.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return entity;
    }
}
