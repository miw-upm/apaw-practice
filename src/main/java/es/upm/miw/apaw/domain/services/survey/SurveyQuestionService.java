package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionTextPatch;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Transactional
    public void patchText(List<SurveyQuestionTextPatch> textPatches) {
        this.assertUniqueIds(textPatches);
        List<SurveyQuestion> surveyQuestions = textPatches.stream()
                .map(update -> {
                    SurveyQuestion surveyQuestion = this.read(update.id());
                    surveyQuestion.setText(update.text());
                    return surveyQuestion;
                })
                .toList();
        surveyQuestions.forEach(this.surveyQuestionGateway::update);
    }

    private void assertUniqueIds(List<SurveyQuestionTextPatch> textPatches) {
        Set<UUID> ids = new HashSet<>();
        for (SurveyQuestionTextPatch update : textPatches) {
            if (!ids.add(update.id())) {
                throw new BadRequestException("Repeated survey question id: " + update.id());
            }
        }
    }
}
