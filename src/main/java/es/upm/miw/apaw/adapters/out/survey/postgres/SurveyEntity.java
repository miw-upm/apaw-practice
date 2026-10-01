package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.survey.Survey;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SurveyEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate createdDate;

    private LocalDate submittedDate;

    @Column(nullable = false)
    private String language;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<SurveyQuestionEntity> surveyQuestions;

    @Column(nullable = false)
    private UUID userId;

    public SurveyEntity(Survey survey) {
        BeanUtils.copyProperties(survey, this, "surveyQuestions", "userSnapshot");
        this.surveyQuestions = survey.getSurveyQuestions().stream()
                .map(SurveyQuestionEntity::new)
                .toList();
        this.userId = survey.getUserSnapshot().getId();
    }

    public Survey toDomain() {
        Survey survey = new Survey();
        BeanUtils.copyProperties(this, survey, "surveyQuestions", "userId");
        survey.setSurveyQuestions(new ArrayList<>(this.surveyQuestions.stream()
                .map(SurveyQuestionEntity::toDomain)
                .toList()));
        survey.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return survey;
    }
}
