package es.upm.miw.apaw.adapters.mongodb.fighters.entities;

import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class MartialArtEntity {
    @Id
    private UUID id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private String discipline;
    private String origin;
    private String description;
    private Boolean striking;
    private Boolean grappling;

    public MartialArtEntity(MartialArt martialArt) {
        BeanUtils.copyProperties(martialArt, this);
        this.id = UUID.randomUUID();
    }

    public void fromMartialArt(MartialArt martialArt) {
        BeanUtils.copyProperties(martialArt, this);
    }

    public MartialArt toMartialArt(){
        MartialArt martialArt = new MartialArt();
        BeanUtils.copyProperties(this, martialArt);
        return martialArt;
    }
}
