package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class EpisodeRepositoryIT {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.episodeRepository.findAll().stream()
                .anyMatch(episode ->
                        12 == episode.getNumber() &&
                                2 == episode.getSeason() &&
                                28 == episode.getDuration() &&
                                "Shingeki No Kyojin".equals(episode.getTvSeriesEntity().getTitle()) &&
                                episode.getId() != null
                ));
    }
}