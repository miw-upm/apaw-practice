package es.upm.miw.apaw.adapters.in.survey;

import es.upm.miw.apaw.domain.model.survey.CreationSurvey;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.services.survey.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SurveyResource.SURVEYS)
@RequiredArgsConstructor
public class SurveyResource {
    public static final String SURVEYS = "/surveys";

    private final SurveyService surveyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Survey create(@Valid @RequestBody CreationSurvey creation) {
        return this.surveyService.create(creation);
    }
}
