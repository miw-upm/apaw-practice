package es.upm.miw.apaw.adapters.mongodb.fighters.entities;

import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MartialArtEntity {
    @Id
    private String discipline;
    private String origin;
    private String description;
    private Boolean striking;
    private Boolean grappling;

    public MartialArtEntity(MartialArt martialArt) {
        BeanUtils.copyProperties(martialArt, this);
    }

    public MartialArt toMartialArt(){
        MartialArt martialArt = new MartialArt();
        BeanUtils.copyProperties(this, martialArt);
        return martialArt;
    }
}
