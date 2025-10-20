package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.ArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.daos.PlaylistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import es.upm.miw.apaw.adapters.mongodb.music.entities.PlaylistEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Playlist;
import es.upm.miw.apaw.domain.persistenceports.music.PlaylistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("playlistPersistenceMongodb")
public class PlaylistPersistenceMongodb implements PlaylistPersistence {
    private final PlaylistRepository playlistRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public PlaylistPersistenceMongodb(PlaylistRepository playlistRepository,
                                      ArtistRepository artistRepository) {
        this.playlistRepository = playlistRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public void delete(String code) {
        if (!this.playlistRepository.existsById(code)) {
            throw new NotFoundException("Playlist not found: " + code);
        }
        this.playlistRepository.deleteById(code);
    }

    @Override
    public void update(String code, Playlist playlist) {
        PlaylistEntity entity = this.playlistRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Playlist not found: " + code));

        if (playlist.getLabel() != null) {
            entity.setLabel(playlist.getLabel());
        }
        if (playlist.getOpened() != null) {
            entity.setOpened(playlist.getOpened());
        }
        this.playlistRepository.save(entity);
    }

    @Override
    public Stream<String> findArtistNamesByLabel(String label) {
        java.util.LinkedHashSet<String> isrcs = this.playlistRepository.findAll().stream()
                .filter(p -> p.getLabel() != null && p.getLabel().equalsIgnoreCase(label))
                .flatMap(p -> p.getSongIsrcs() == null ? Stream.empty() : p.getSongIsrcs().stream())
                .collect(java.util.stream.Collectors.toCollection(java.util.LinkedHashSet::new));

        if (isrcs.isEmpty()) {
            return Stream.empty();
        }

        return this.artistRepository.findAll().stream()
                .filter(a -> a.getSongIsrcs() != null && !java.util.Collections.disjoint(a.getSongIsrcs(), isrcs))
                .map(ArtistEntity::getName)
                .filter(Objects::nonNull)
                .distinct()
                .sorted();
    }
}
