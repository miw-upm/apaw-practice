package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.StyleRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Style;
import es.upm.miw.apaw.domain.persistenceports.music.StylePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("stylePersistenceMongodb")
public class StylePersistenceMongodb implements StylePersistence {

    private final StyleRepository styleRepository;

    @Autowired
    public StylePersistenceMongodb(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void patch(String genre, Style style) {
        StyleEntity entity = this.styleRepository.findById(genre)
                .orElseThrow(() -> new NotFoundException("Style not found: " + genre));

        if (style.getPopularityIndex() != null) {
            int idx = style.getPopularityIndex();
            if (idx < 0 || idx > 100) {
                throw new BadRequestException("popularityIndex must be between 0 and 100");
            }
            entity.setPopularityIndex(idx);
        }
        if (style.getMood() != null) {
            String mood = style.getMood().trim();
            if (mood.isEmpty()) {
                throw new BadRequestException("mood cannot be blank");
            }
            entity.setMood(mood);
        }

        this.styleRepository.save(entity);
    }
}
