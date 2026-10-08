package es.upm.miw.apaw.adapters.in.survey;

import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionTextPatch;
import es.upm.miw.apaw.domain.services.survey.SurveyQuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(SurveyQuestionResource.SURVEY_QUESTIONS)
@RequiredArgsConstructor
public class SurveyQuestionResource {
    public static final String SURVEY_QUESTIONS = "/survey-questions";
    public static final String ID = "/{id}";

    private final SurveyQuestionService surveyQuestionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyQuestion create(@Valid @RequestBody SurveyQuestion surveyQuestion) {
        return this.surveyQuestionService.create(surveyQuestion);
    }

    @GetMapping
    public List<SurveyQuestion> findAll() {
        return this.surveyQuestionService.findAll();
    }

    @GetMapping(ID)
    public SurveyQuestion read(@PathVariable UUID id) {
        return this.surveyQuestionService.read(id);
    }

    @PutMapping(ID)
    public SurveyQuestion update(@PathVariable UUID id, @Valid @RequestBody SurveyQuestion surveyQuestion) {
        return this.surveyQuestionService.update(id, surveyQuestion);
    }

    @PatchMapping
    public void patchText(@RequestBody @NotEmpty List<@NotNull @Valid SurveyQuestionTextPatch> textPatches) {
        this.surveyQuestionService.patchText(textPatches);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.surveyQuestionService.delete(id);
    }
}
