package es.upm.miw.apaw.functionaltests.survey;

import es.upm.miw.apaw.adapters.in.survey.SurveyQuestionResource;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionTextPatch;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SurveyQuestionSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SurveyQuestionResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(SurveyQuestionResource.SURVEY_QUESTIONS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SurveyQuestion.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(QUESTION_0));
    }

    @Test
    void testReadNotFound() {
        UUID missingId = UUID.randomUUID();
        this.restTestClient.get().uri(SurveyQuestionResource.SURVEY_QUESTIONS + "/" + missingId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(missingId.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(SurveyQuestionResource.SURVEY_QUESTIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SurveyQuestion[].class)
                .value(body -> assertThat(body).extracting(SurveyQuestion::getText)
                        .containsSubsequence(
                                QUESTION_0.getText(),
                                QUESTION_1.getText(),
                                QUESTION_2.getText()
                        ));
    }

    @Test
    void testCreate() {
        SurveyQuestion created = this.createQuestion();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getText()).isEqualTo("Favorite language");
        assertThat(created.getSurveyQuestionType()).isEqualTo(SurveyQuestionType.TEXT);
    }

    @Test
    void testCreateBlankText() {
        this.restTestClient.post().uri(SurveyQuestionResource.SURVEY_QUESTIONS)
                .body(SurveyQuestion.builder().text(" ").surveyQuestionType(SurveyQuestionType.TEXT)
                        .options(List.of("Java", "Kotlin")).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdate() {
        SurveyQuestion created = this.createQuestion();
        SurveyQuestion updated = SurveyQuestion.builder()
                .id(created.getId())
                .text("Updated question")
                .surveyQuestionType(SurveyQuestionType.YES_NO)
                .required(false)
                .maxLength(5)
                .options(List.of("Yes", "No"))
                .build();

        this.restTestClient.put().uri(SurveyQuestionResource.SURVEY_QUESTIONS + "/" + created.getId())
                .body(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SurveyQuestion.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(created.getId());
                    assertThat(body.getText()).isEqualTo("Updated question");
                    assertThat(body.getSurveyQuestionType()).isEqualTo(SurveyQuestionType.YES_NO);
                    assertThat(body.getOptions()).containsExactly("Yes", "No");
                });
    }

    @Test
    void testDelete() {
        SurveyQuestion created = this.createQuestion();
        this.restTestClient.delete().uri(SurveyQuestionResource.SURVEY_QUESTIONS + "/" + created.getId())
                .exchange()
                .expectStatus().isNoContent();

        this.restTestClient.get().uri(SurveyQuestionResource.SURVEY_QUESTIONS + "/" + created.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchText() {
        SurveyQuestion first = this.createQuestion();
        SurveyQuestion second = this.createQuestion();

        this.restTestClient.patch().uri(SurveyQuestionResource.SURVEY_QUESTIONS)
                .body(List.of(
                        new SurveyQuestionTextPatch(first.getId(), "First updated text"),
                        new SurveyQuestionTextPatch(second.getId(), "Second updated text")))
                .exchange()
                .expectStatus().isOk();

        this.assertText(first.getId(), "First updated text");
        this.assertText(second.getId(), "Second updated text");
    }

    @Test
    void testPatchTextRepeatedIdChangesNothing() {
        SurveyQuestion created = this.createQuestion();

        this.restTestClient.patch().uri(SurveyQuestionResource.SURVEY_QUESTIONS)
                .body(List.of(
                        new SurveyQuestionTextPatch(created.getId(), "First updated text"),
                        new SurveyQuestionTextPatch(created.getId(), "Second updated text")))
                .exchange()
                .expectStatus().isBadRequest();

        this.assertText(created.getId(), created.getText());
    }

    private SurveyQuestion createQuestion() {
        return this.restTestClient.post().uri(SurveyQuestionResource.SURVEY_QUESTIONS)
                .body(SurveyQuestion.builder()
                        .text("Favorite language")
                        .surveyQuestionType(SurveyQuestionType.TEXT)
                        .options(List.of("Java", "Kotlin", "Go"))
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(SurveyQuestion.class)
                .returnResult().getResponseBody();
    }

    private void assertText(UUID id, String expectedText) {
        this.restTestClient.get().uri(SurveyQuestionResource.SURVEY_QUESTIONS + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SurveyQuestion.class)
                .value(body -> assertThat(body).isNotNull().extracting(SurveyQuestion::getText)
                        .isEqualTo(expectedText));
    }
}
