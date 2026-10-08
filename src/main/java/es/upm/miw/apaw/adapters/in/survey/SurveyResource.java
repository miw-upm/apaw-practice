package es.upm.miw.apaw.adapters.in.survey;

import es.upm.miw.apaw.domain.model.survey.CreationSurvey;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.survey.SurveyFindCriteria;
import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;
import es.upm.miw.apaw.domain.services.survey.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SurveyResource.SURVEYS)
@RequiredArgsConstructor
public class SurveyResource {
    public static final String SURVEYS = "/surveys";
    public static final String REPORT = "/report";

    private final SurveyService surveyService;

    @GetMapping
    public List<Survey> find(@ModelAttribute SurveyFindCriteria criteria) {
        return this.surveyService.find(criteria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Survey create(@Valid @RequestBody CreationSurvey creation) {
        return this.surveyService.create(creation);
    }

    @GetMapping(REPORT)
    public List<SurveyUserLanguageReport> findUserLanguageReport() {
        return this.surveyService.findUserLanguageReport();
    }
}
