package es.upm.miw.apaw.adapters.mongodb.sports.academy.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sports_academy_legal_guardians")
public class LegalGuardianEntity {
    @Id
    private UUID userDtoId;
    private String secondMobile;
    private int relationShip;

    public LegalGuardianEntity(LegalGuardian legalGuardian) {
        BeanUtils.copyProperties(legalGuardian, this);
        userDtoId = legalGuardian.getUser().getId();
    }

    public void fromLegalGuardian(LegalGuardian legalGuardian) {
        BeanUtils.copyProperties(legalGuardian, this);
        userDtoId = legalGuardian.getUser().getId();
    }

    public LegalGuardian toLegalGuardian() {
        LegalGuardian legalGuardian = new LegalGuardian();
        BeanUtils.copyProperties(this, legalGuardian);
        legalGuardian.setUser(UserDto.builder().id(userDtoId).build());
        return legalGuardian;
    }
}
