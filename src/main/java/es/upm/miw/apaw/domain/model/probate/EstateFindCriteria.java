package es.upm.miw.apaw.domain.model.probate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstateFindCriteria {
    private String fileNumber;
    private Boolean opened;
    private HeirStatus heirStatus;
    private String userMobile;
}
