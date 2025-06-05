package es.upm.miw.apaw_practice.domain.services.music_festival;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.music_festival.*;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.ConcertArtistPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.StagePersistence;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicFestivalService {

    private final MusicFestivalPersistence musicFestivalPersistence;
    private final StagePersistence stagePersistence;
    private final ConcertArtistPersistence concertArtistPersistence;

    @Autowired
    public MusicFestivalService(MusicFestivalPersistence musicFestivalPersistence,
                                StagePersistence stagePersistence,
                                ConcertArtistPersistence concertArtistPersistence) {
        this.musicFestivalPersistence = musicFestivalPersistence;
        this.stagePersistence = stagePersistence;
        this.concertArtistPersistence = concertArtistPersistence;
    }

    public void updateBudgets(Stream<MusicFestivalBudgetUpdating> budgetUpdatingList) {
        budgetUpdatingList.map(budgetUpdating -> {
                    MusicFestival festival = this.musicFestivalPersistence.readByName(budgetUpdating.getName());
                    festival.setBudget(budgetUpdating.getBudget());
                    return festival;
                })
                .forEach(festival -> this.musicFestivalPersistence.updateBudget(festival.getName(),
                        festival.getBudget()));
    }

    public MusicFestival updateConcerts(String name, List<Concert> concertList) {
        this.assertConcertListNotEmpty(concertList);
        this.assertConcertsHaveStageName(concertList);
        this.assertConcertsHaveArtists(concertList);
        this.assertNoDuplicateArtists(concertList);
        this.assertNoDuplicateConcerts(concertList);
        this.assertStageOpenTimesBeforeEqualsDateConcert(concertList);
        this.assertArtistsExist(concertList);
        Set<String> newConcertCodeIdentifiers = this.getConcertCodeIdentifiers(concertList);
        List<MusicFestival> otherFestivals = this.fetchOtherFestivals(name);
        this.assertNoGlobalConcertDuplicates(otherFestivals, newConcertCodeIdentifiers);
        MusicFestival originalFestival = this.musicFestivalPersistence.readByName(name);
        this.assertNoOrphanArtists(concertList, otherFestivals, originalFestival);
        originalFestival.setConcerts(concertList);
        return this.musicFestivalPersistence.update(originalFestival);
    }

    private void assertConcertListNotEmpty(List<Concert> concertList) {
        if (concertList == null || concertList.isEmpty()) {
            throw new BadRequestException("MusicFestival must have at least one concert");
        }
    }

    private void assertConcertsHaveStageName(List<Concert> concertList) {
        if (concertList.stream().anyMatch(concert ->
                concert.getStage() == null
                        || concert.getStage().getName() == null
                        || concert.getStage().getName().isEmpty())) {
            throw new BadRequestException("Each concert must have a stage with a name");
        }
    }

    private void assertConcertsHaveArtists(List<Concert> concertList) {
        if (concertList.stream().anyMatch(concert ->
                concert.getArtists() == null || concert.getArtists().isEmpty())) {
            throw new BadRequestException("Each concert must have at least one artist");
        }
    }

    private void assertNoDuplicateArtists(List<Concert> concertList) {
        if (concertList.stream().anyMatch(concert ->
                concert.getArtists().stream()
                        .map(ConcertArtist::getName)
                        .distinct()
                        .count() < concert.getArtists().size())) {
            throw new ConflictException("Duplicate artists in the same concert are not allowed");
        }
    }

    private void assertNoDuplicateConcerts(List<Concert> concertList) {
        if (concertList.stream()
                .map(concert -> concert.getStage().getName() + ":" + concert.getDate())
                .distinct()
                .count() < concertList.size()) {
            throw new ConflictException("Duplicate concerts in the same stage and date are not allowed");
        }
    }

    private void assertStageOpenTimesBeforeEqualsDateConcert(List<Concert> concertList) {
        if (concertList.stream()
                .anyMatch(concert -> {
                    Stage stage = this.stagePersistence.readByName(concert.getStage().getName());
                    return stage.getOpenTime() != null
                            && stage.getOpenTime().toLocalDate().isAfter(concert.getDate());
                })) {
            throw new BadRequestException("Stage open time must not be after concert date");
        }
    }

    private void assertArtistsExist(List<Concert> concertList) {
        concertList.stream()
                .flatMap(concert -> concert.getArtists().stream().map(ConcertArtist::getName))
                .distinct()
                .forEach(this.concertArtistPersistence::readByName);
    }

    private Set<String> getConcertCodeIdentifiers(List<Concert> concertList) {
        return concertList.stream()
                .map(concert -> concert.getStage().getName() + ":" + concert.getDate())
                .collect(Collectors.toSet());
    }

    private List<MusicFestival> fetchOtherFestivals(String name) {
        return this.musicFestivalPersistence.readAll()
                .filter(festival -> !festival.getName().equals(name))
                .toList();
    }

    private void assertNoGlobalConcertDuplicates(List<MusicFestival> otherFestivals, Set<String> newConcertCombos) {
        if (otherFestivals.stream()
                .flatMap(festival -> festival.getConcerts().stream()
                        .map(concert -> concert.getStage().getName() + ":" + concert.getDate()))
                .distinct()
                .anyMatch(newConcertCombos::contains)) {
            throw new ConflictException("A concert with the same stage and date already exists in another festival");
        }
    }

    private void assertNoOrphanArtists(List<Concert> concertList, List<MusicFestival> otherFestivals,
                                       MusicFestival originalFestival) {
        Set<String> newArtists = concertList.stream()
                .flatMap(concert -> concert.getArtists().stream().map(ConcertArtist::getName)).collect(Collectors.toSet());
        Set<String> otherFestivalArtists = otherFestivals.stream()
                .flatMap(festival -> festival.getConcerts().stream())
                .flatMap(concert -> concert.getArtists().stream().map(ConcertArtist::getName)).collect(Collectors.toSet());
        boolean orphanArtist = originalFestival.getConcerts().stream()
                .flatMap(concert -> concert.getArtists().stream().map(ConcertArtist::getName))
                .distinct()
                .filter(artist -> !newArtists.contains(artist))
                .anyMatch(artist -> !otherFestivalArtists.contains(artist));
        if (orphanArtist) {
            throw new BadRequestException("Operation not allowed: some artists would have no concerts left");
        }
    }

}