package es.upm.miw.apaw.domain.model.copyright;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreativeWorkFindCriteria {
    private String authorPenName;
    private Boolean isHighlyValued;
    private Boolean claimUrgent;
    private String authorFirstName;

    public boolean hasAuthorFirstName() {
        return this.authorFirstName != null;
    }
}
