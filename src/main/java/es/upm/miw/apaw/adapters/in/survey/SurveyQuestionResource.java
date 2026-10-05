package es.upm.miw.apaw.adapters.in.survey;

import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.services.survey.SurveyQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SurveyQuestionResource.SURVEY_QUESTIONS)
@RequiredArgsConstructor
public class SurveyQuestionResource {
    public static final String SURVEY_QUESTIONS = "/survey-questions";

    private final SurveyQuestionService surveyQuestionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyQuestion create(@Valid @RequestBody SurveyQuestion surveyQuestion) {
        return this.surveyQuestionService.create(surveyQuestion);
    }
}
