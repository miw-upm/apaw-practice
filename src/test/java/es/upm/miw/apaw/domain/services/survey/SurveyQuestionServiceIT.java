package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyQuestionEntity;
import es.upm.miw.apaw.adapters.out.survey.postgres.SurveyRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionTextPatch;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SurveyQuestionSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SurveyQuestionServiceIT {
    @Autowired
    private SurveyQuestionService surveyQuestionService;
    @Autowired
    private SurveyRepository surveyRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.surveyQuestionService.read(ID_0)).usingRecursiveComparison().isEqualTo(QUESTION_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.surveyQuestionService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalQuestions() {
        SurveyQuestion extra = this.createQuestion();
        List<SurveyQuestion> questions = this.surveyQuestionService.findAll();
        assertThat(questions).extracting(SurveyQuestion::getId).contains(ID_0, ID_1, ID_2, extra.getId());
        assertThat(questions).extracting(SurveyQuestion::getText)
                .containsSubsequence(QUESTION_0.getText(), QUESTION_1.getText(), QUESTION_2.getText());
        assertThat(this.surveyQuestionService.findAll()).extracting(SurveyQuestion::getId)
                .containsExactlyElementsOf(questions.stream().map(SurveyQuestion::getId).toList());
    }

    @Test
    void testCreateAppliesDefaults() {
        SurveyQuestion created = this.createQuestion();
        SurveyQuestion stored = this.surveyQuestionService.read(created.getId());
        assertThat(stored).usingRecursiveComparison().isEqualTo(created);
        assertThat(stored.getRequired()).isTrue();
    }

    @Test
    void testUpdateReplacesMutableFields() {
        SurveyQuestion original = this.createQuestion();
        SurveyQuestion replacement = SurveyQuestion.builder()
                .text("Updated " + UUID.randomUUID())
                .surveyQuestionType(SurveyQuestionType.YES_NO)
                .required(false)
                .maxLength(3)
                .options(List.of("Yes", "No"))
                .build();
        this.surveyQuestionService.update(original.getId(), replacement);
        SurveyQuestion updated = this.surveyQuestionService.read(original.getId());
        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getText()).isEqualTo(replacement.getText());
        assertThat(updated.getSurveyQuestionType()).isEqualTo(SurveyQuestionType.YES_NO);
        assertThat(updated.getRequired()).isFalse();
        assertThat(updated.getMaxLength()).isEqualTo(3);
        assertThat(updated.getOptions()).containsExactly("Yes", "No");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.surveyQuestionService.update(id, QUESTION_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        SurveyQuestion question = this.createQuestion();
        this.surveyQuestionService.delete(question.getId());
        assertThatThrownBy(() -> this.surveyQuestionService.read(question.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingQuestion() {
        UUID id = UUID.randomUUID();
        this.surveyQuestionService.delete(id);
        assertThatThrownBy(() -> this.surveyQuestionService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedQuestion() {
        SurveyQuestion question = this.createQuestion();
        SurveyEntity survey = SurveyEntity.builder().id(UUID.randomUUID())
                .title("Survey " + UUID.randomUUID()).description("Survey description")
                .createdDate(LocalDate.of(2025, 1, 1)).language("Spanish").userId(UUID.randomUUID())
                .surveyQuestions(List.of(new SurveyQuestionEntity(question))).build();
        this.surveyRepository.saveAndFlush(survey);
        assertThatThrownBy(() -> this.surveyQuestionService.delete(question.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(question.getId().toString());
        assertThat(this.surveyQuestionService.read(question.getId()).getId()).isEqualTo(question.getId());
        assertThat(this.surveyRepository.existsBySurveyQuestionsId(question.getId())).isTrue();
    }

    @Test
    void testPatchText() {
        SurveyQuestion first = this.createQuestion();
        SurveyQuestion second = this.createQuestion();
        this.surveyQuestionService.patchText(List.of(
                new SurveyQuestionTextPatch(first.getId(), "First updated text"),
                new SurveyQuestionTextPatch(second.getId(), "Second updated text")));
        assertThat(this.surveyQuestionService.read(first.getId()).getText()).isEqualTo("First updated text");
        assertThat(this.surveyQuestionService.read(second.getId()).getText()).isEqualTo("Second updated text");
        assertThat(this.surveyQuestionService.read(first.getId()).getOptions())
                .containsExactlyElementsOf(first.getOptions());
    }

    @Test
    void testPatchTextNotFoundChangesNothing() {
        SurveyQuestion question = this.createQuestion();
        UUID missingId = UUID.randomUUID();
        assertThatThrownBy(() -> this.surveyQuestionService.patchText(List.of(
                new SurveyQuestionTextPatch(question.getId(), "Changed text"),
                new SurveyQuestionTextPatch(missingId, "Missing text"))))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.surveyQuestionService.read(question.getId()).getText()).isEqualTo(question.getText());
    }

    @Test
    void testPatchTextDuplicateIdChangesNothing() {
        SurveyQuestion question = this.createQuestion();
        assertThatThrownBy(() -> this.surveyQuestionService.patchText(List.of(
                new SurveyQuestionTextPatch(question.getId(), "First text"),
                new SurveyQuestionTextPatch(question.getId(), "Second text"))))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(question.getId().toString());
        assertThat(this.surveyQuestionService.read(question.getId()).getText()).isEqualTo(question.getText());
    }

    private SurveyQuestion createQuestion() {
        return this.surveyQuestionService.create(SurveyQuestion.builder()
                .text("IT question " + UUID.randomUUID())
                .surveyQuestionType(SurveyQuestionType.TEXT)
                .maxLength(50)
                .options(List.of("One", "Two", "Three"))
                .build());
    }
}
