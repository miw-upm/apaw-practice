package es.upm.miw.apaw.domain.model.survey;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Survey {

    @EqualsAndHashCode.Include
    private UUID id;

    private String title;

    private String description;

    private LocalDate createdDate;

    private LocalDate submittedDate;

    private String language;

    private List<SurveyQuestion> surveyQuestions;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdDate = LocalDate.now();
        if (this.language == null) {
            this.language = "Spanish";
        }
    }

    public boolean isSubmitted() {
        return this.submittedDate != null;
    }

    public Survey ofSummary() {
        return Survey.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .createdDate(this.createdDate)
                .submittedDate(this.submittedDate)
                .language(this.language)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}
