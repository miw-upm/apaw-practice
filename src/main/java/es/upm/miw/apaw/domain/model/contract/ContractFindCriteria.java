package es.upm.miw.apaw.domain.model.contract;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractFindCriteria {

    private String title;

    private Boolean active;

    private ClauseType clauseType;

    private String userCity;

    public Boolean isAll() {
        return !this.hasTitle() && !this.hasActive()
                && !this.hasClauseType() && !this.hasUserCity();
    }

    public boolean hasTitle() {
        return this.title != null && !this.title.isBlank();
    }

    public boolean hasActive() {
        return this.active != null;
    }

    public boolean hasClauseType() {
        return this.clauseType != null;
    }

    public boolean hasUserCity() {return this.userCity != null && !this.userCity.isBlank();}
}