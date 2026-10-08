package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionRepository;
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

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class SurveyQuestionSeederForDev implements ApplicationRunner {
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

    private final SurveyQuestionRepository surveyQuestionRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        List<SurveyQuestionEntity> surveyQuestions = List.of(QUESTION_0, QUESTION_1, QUESTION_2).stream()
                .filter(question -> !this.surveyQuestionRepository.existsById(question.getId()))
                .map(SurveyQuestionEntity::new)
                .toList();
        this.surveyQuestionRepository.saveAll(surveyQuestions);
        log.warn("        ------- survey questions: {} added", surveyQuestions.size());
    }
}
