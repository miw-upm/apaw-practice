package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyQuestionService {
    private final SurveyQuestionGateway surveyQuestionGateway;

    public SurveyQuestion create(SurveyQuestion surveyQuestion) {
        surveyQuestion.doDefault();
        return this.surveyQuestionGateway.create(surveyQuestion);
    }

    public SurveyQuestion read(UUID id) {
        return this.surveyQuestionGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Survey question id not found: " + id));
    }

    public SurveyQuestion update(UUID id, SurveyQuestion surveyQuestion) {
        SurveyQuestion storedSurveyQuestion = this.read(id);
        storedSurveyQuestion.setText(surveyQuestion.getText());
        storedSurveyQuestion.setSurveyQuestionType(surveyQuestion.getSurveyQuestionType());
        storedSurveyQuestion.setRequired(surveyQuestion.getRequired());
        storedSurveyQuestion.setMaxLength(surveyQuestion.getMaxLength());
        storedSurveyQuestion.setOptions(surveyQuestion.getOptions());
        return this.surveyQuestionGateway.update(storedSurveyQuestion);
    }

    public void delete(UUID id) {
        if (this.surveyQuestionGateway.isReferenced(id)) {
            throw new ConflictException("Survey question is referenced by a survey: " + id);
        }
        this.surveyQuestionGateway.delete(id);
    }

    public List<SurveyQuestion> findAll() {
        return this.surveyQuestionGateway.findAll();
    }
}
