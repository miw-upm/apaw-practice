package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.survey.CreationSurvey;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyFindCriteria;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionType;
import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.upm.miw.apaw.config.seeders.SurveySeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    void testFindUserLanguageReportHydratesUsersInOneCall() {
        this.mockHydratedUsers();

        List<SurveyUserLanguageReport> reports = this.surveyService.findUserLanguageReport();

        assertThat(reports)
                .filteredOn(report -> report.getUserSnapshot().getId().equals(SURVEY_0.getUserSnapshot().getId()))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getUserSnapshot().getMobile()).isNotNull();
                    assertThat(report.getUserSnapshot().getFirstName()).isNotNull();
                });
        assertThat(reports).extracting(SurveyUserLanguageReport::getSurveyQuestionCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        verify(this.userFinder, times(1)).findByIds(any());
        verify(this.userFinder, never()).read(any());
    }

    @Test
    void testFindUserLanguageReportGroupsByUserAndLanguage() {
        this.mockHydratedUsers();
        UserSnapshot user = this.mockUser();
        this.surveyService.create(this.creation(user.getId(), ID_0, ID_1));
        CreationSurvey english = this.creation(user.getId(), ID_2);
        english.setLanguage("English");
        this.surveyService.create(english);
        CreationSurvey otherEnglish = this.creation(user.getId(), ID_0, ID_1);
        otherEnglish.setLanguage("English");
        this.surveyService.create(otherEnglish);

        List<SurveyUserLanguageReport> userReports = this.surveyService.findUserLanguageReport().stream()
                .filter(report -> report.getUserSnapshot().getId().equals(user.getId()))
                .toList();

        assertThat(userReports).hasSize(2);
        assertThat(userReports).filteredOn(report -> report.getLanguage().equals("Spanish"))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getSurveyCount()).isEqualTo(1);
                    assertThat(report.getSurveyQuestionCount()).isEqualTo(2);
                });
        assertThat(userReports).filteredOn(report -> report.getLanguage().equals("English"))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getSurveyCount()).isEqualTo(2);
                    assertThat(report.getSurveyQuestionCount()).isEqualTo(3);
                });
    }

    @Test
    void testFindUserLanguageReportUserNotReturnedKeepsUserId() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of());

        List<SurveyUserLanguageReport> reports = this.surveyService.findUserLanguageReport();

        assertThat(reports).isNotEmpty().allSatisfy(report -> {
            assertThat(report.getUserSnapshot().getId()).isNotNull();
            assertThat(report.getUserSnapshot().getMobile()).isNull();
        });
        verify(this.userFinder, times(1)).findByIds(any());
    }

    private void mockHydratedUsers() {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Set<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream()
                    .map(id -> UserSnapshot.builder().id(id).mobile("600000999").firstName("encuestado").build())
                    .toList();
        });
    }

    @Test
    void testFindByLanguage() {
        this.mockSeededUsers();

        List<Survey> surveys = this.surveyService.find(SurveyFindCriteria.builder().language("English").build());

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_0, SURVEY_ID_2)
                .doesNotContain(SURVEY_ID_1);
        assertThat(surveys).extracting(Survey::getLanguage).containsOnly("English");
    }

    @Test
    void testFindSubmitted() {
        this.mockSeededUsers();

        List<Survey> surveys = this.surveyService.find(SurveyFindCriteria.builder().submitted(true).build());

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_0)
                .doesNotContain(SURVEY_ID_1, SURVEY_ID_2);
        assertThat(surveys).extracting(Survey::getSubmittedDate).doesNotContainNull();
    }

    @Test
    void testFindNotSubmitted() {
        this.mockSeededUsers();

        List<Survey> surveys = this.surveyService.find(SurveyFindCriteria.builder().submitted(false).build());

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_1, SURVEY_ID_2)
                .doesNotContain(SURVEY_ID_0);
        assertThat(surveys).extracting(Survey::getSubmittedDate).containsOnlyNulls();
    }

    @Test
    void testFindBySurveyQuestionType() {
        this.mockSeededUsers();

        List<Survey> surveys = this.surveyService.find(
                SurveyFindCriteria.builder().surveyQuestionType(SurveyQuestionType.TEXT).build());

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_1, SURVEY_ID_2)
                .doesNotContain(SURVEY_ID_0).doesNotHaveDuplicates();
    }

    @Test
    void testFindByUserCityIgnoringCase() {
        this.mockSeededUsers();

        List<Survey> surveys = this.surveyService.find(SurveyFindCriteria.builder().userCity("madrid").build());

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_0, SURVEY_ID_2)
                .doesNotContain(SURVEY_ID_1);
        assertThat(surveys).extracting(survey -> survey.getUserSnapshot().getId())
                .doesNotContain(SURVEY_1.getUserSnapshot().getId());
    }

    @Test
    void testFindCombinedCriteria() {
        this.mockSeededUsers();
        SurveyFindCriteria criteria = SurveyFindCriteria.builder()
                .language("English").submitted(false).surveyQuestionType(SurveyQuestionType.YES_NO)
                .userCity("Madrid").build();

        List<Survey> surveys = this.surveyService.find(criteria);

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_2)
                .doesNotContain(SURVEY_ID_0, SURVEY_ID_1);
    }

    @Test
    void testFindWithoutCriteriaReturnsSummariesWithOneUserCall() {
        this.mockSeededUsers();

        List<Survey> surveys = this.surveyService.find(new SurveyFindCriteria());

        assertThat(surveys).extracting(Survey::getId).contains(SURVEY_ID_0, SURVEY_ID_1, SURVEY_ID_2);
        assertThat(surveys).extracting(Survey::getSurveyQuestions).containsOnlyNulls();
        assertThat(surveys).allSatisfy(survey ->
                assertThat(survey.getUserSnapshot().getFirstName()).isNotNull());
        verify(this.userFinder, times(1)).findByIds(any());
        verify(this.userFinder, never()).read(any());
    }

    @Test
    void testFindUserNotFound() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of());

        assertThatThrownBy(() -> this.surveyService.find(new SurveyFindCriteria()))
                .isInstanceOf(NotFoundException.class).hasMessageContaining("User id not found");
    }

    private void mockSeededUsers() {
        Map<UUID, UserSnapshot> seededUsers = Stream.of(SURVEY_0, SURVEY_1, SURVEY_2)
                .map(Survey::getUserSnapshot)
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Set<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream()
                    .map(id -> seededUsers.getOrDefault(id, UserSnapshot.builder()
                            .id(id).mobile("600000999").firstName("encuestado").city("Other").build()))
                    .toList();
        });
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
