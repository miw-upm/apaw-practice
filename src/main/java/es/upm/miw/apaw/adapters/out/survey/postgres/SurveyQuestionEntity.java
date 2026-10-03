package es.upm.miw.apaw.adapters.out.survey.postgres;

import es.upm.miw.apaw.domain.model.survey.SurveyQuestion;
import es.upm.miw.apaw.domain.model.survey.SurveyQuestionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SurveyQuestionEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurveyQuestionType type;

    @Column(nullable = false)
    private Boolean required;

    private Integer maxLength;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<String> options;

    public SurveyQuestionEntity(SurveyQuestion surveyQuestion) {
        BeanUtils.copyProperties(surveyQuestion, this);
    }

    public SurveyQuestion toDomain() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        BeanUtils.copyProperties(this, surveyQuestion);
        return surveyQuestion;
    }
}
