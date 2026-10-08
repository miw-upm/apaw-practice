package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.survey.CreationSurvey;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SurveySeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class SurveyServiceIT {
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyRepository surveyRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = this.mockUser();
        CreationSurvey creation = this.creation(user.getId(), ID_0, ID_1);

        Survey survey = this.surveyService.create(creation);

        assertThat(survey.getId()).isNotNull();
        assertThat(survey.getCreatedDate()).isEqualTo(LocalDate.now());
        assertThat(survey.getSubmittedDate()).isNull();
        assertThat(survey.getSurveyQuestions()).extracting(SurveyQuestion::getId).containsExactly(ID_0, ID_1);
        assertThat(survey.getUserSnapshot()).isEqualTo(user);
        SurveyEntity entity = this.surveyRepository.findById(survey.getId()).orElseThrow();
        assertThat(entity.getTitle()).isEqualTo(creation.getTitle());
        assertThat(entity.getDescription()).isEqualTo(creation.getDescription());
        assertThat(entity.getSurveyQuestions()).extracting(SurveyQuestionEntity::getId)
                .containsExactly(ID_0, ID_1);
        assertThat(entity.getUserId()).isEqualTo(user.getId());
    }

    @Test
    @Transactional
    void testCreateDefaultLanguage() {
        UserSnapshot user = this.mockUser();
        Survey survey = this.surveyService.create(this.creation(user.getId(), ID_0));
        assertThat(survey.getLanguage()).isEqualTo("Spanish");
    }

    @Test
    @Transactional
    void testCreateKeepsLanguage() {
        UserSnapshot user = this.mockUser();
        CreationSurvey creation = this.creation(user.getId(), ID_0);
        creation.setLanguage("English");
        Survey survey = this.surveyService.create(creation);
        assertThat(survey.getLanguage()).isEqualTo("English");
    }

    @Test
    void testCreateDuplicateTitle() {
        UserSnapshot user = this.mockUser();
        CreationSurvey creation = this.creation(user.getId(), ID_0);
        creation.setTitle(SURVEY_0.getTitle());
        assertThatThrownBy(() -> this.surveyService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(SURVEY_0.getTitle());
    }

    @Test
    void testCreateSurveyQuestionNotFoundSavesNothing() {
        UserSnapshot user = this.mockUser();
        UUID missingId = UUID.randomUUID();
        CreationSurvey creation = this.creation(user.getId(), ID_0, missingId);
        assertThatThrownBy(() -> this.surveyService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.surveyRepository.existsByTitle(creation.getTitle())).isFalse();
    }

    @Test
    void testCreateUserNotFoundSavesNothing() {
        UUID userId = UUID.randomUUID();
        when(this.userFinder.read(userId)).thenThrow(new NotFoundException("User id not found: " + userId));
        CreationSurvey creation = this.creation(userId, ID_0);
        assertThatThrownBy(() -> this.surveyService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(userId.toString());
        assertThat(this.surveyRepository.existsByTitle(creation.getTitle())).isFalse();
    }

    private UserSnapshot mockUser() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.randomUUID())
                .mobile("600000999")
                .firstName("encuestado")
                .build();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        return user;
    }

    private CreationSurvey creation(UUID userId, UUID... surveyQuestionIds) {
        return CreationSurvey.builder()
                .title("IT survey " + UUID.randomUUID())
                .description("IT survey description")
                .surveyQuestionIds(List.of(surveyQuestionIds))
                .userId(userId)
                .build();
    }
}
