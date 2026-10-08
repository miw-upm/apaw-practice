package es.upm.miw.apaw.domain.services.survey;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.survey.CreationSurvey;
import es.upm.miw.apaw.domain.model.survey.Survey;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyUserLanguageReport;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyGateway;
import es.upm.miw.apaw.domain.ports.out.survey.SurveyQuestionGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyGateway surveyGateway;
    private final SurveyQuestionGateway surveyQuestionGateway;
    private final UserFinder userFinder;

    public Survey create(CreationSurvey creation) {
        if (this.surveyGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Survey title already exists: " + creation.getTitle());
        }
        Survey survey = new Survey();
        BeanUtils.copyProperties(creation, survey);
        survey.setSurveyQuestions(creation.getSurveyQuestionIds().stream()
                .map(this::readSurveyQuestion)
                .toList());
        survey.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        survey.doDefault();
        return this.surveyGateway.create(survey);
    }

    public List<SurveyUserLanguageReport> findUserLanguageReport() {
        List<SurveyUserLanguageReport> reports = this.surveyGateway.findUserLanguageReport();
        if (reports.isEmpty()) {
            return reports;
        }
        Set<UUID> userIds = reports.stream()
                .map(report -> report.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> users = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        reports.forEach(report -> report.setUserSnapshot(
                users.getOrDefault(report.getUserSnapshot().getId(), report.getUserSnapshot())));
        return reports;
    }

    private SurveyQuestion readSurveyQuestion(UUID id) {
        return this.surveyQuestionGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Survey question id not found: " + id));
    }
}
