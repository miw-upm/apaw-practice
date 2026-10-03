package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingStatus;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingType;
import es.upm.miw.apaw.domain.model.courthearing.CourtType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class CourtRepositoryIT {
    @Autowired
    private CourtRepository courtRepository;
    @Autowired
    private CourtHearingRepository courtHearingRepository;

    @Test
    void testConversionKeepsIdentityAndFields() {
        CourtEntity entity = this.courtRepository.findById(COURT_ID_0).orElseThrow();
        assertThat(entity.toDomain()).usingRecursiveComparison().isEqualTo(COURT_0);
        assertThat(new CourtEntity(COURT_0).getId()).isEqualTo(COURT_ID_0);
    }

    @Test
    void testExistsByName() {
        assertThat(this.courtRepository.existsByName(COURT_0.getName())).isTrue();
        assertThat(this.courtRepository.existsByName("Missing " + UUID.randomUUID())).isFalse();
    }

    @Test
    void testExistsByPhone() {
        assertThat(this.courtRepository.existsByPhone(COURT_0.getPhone())).isTrue();
        assertThat(this.courtRepository.existsByPhone("missing-" + UUID.randomUUID())).isFalse();
    }

    @Test
    void testFindAllByOrderByNameAsc() {
        List<CourtEntity> courts = this.courtRepository.findAllByOrderByNameAsc();
        assertThat(courts).extracting(CourtEntity::getId)
                .contains(COURT_ID_0, COURT_ID_1, COURT_ID_2, COURT_ID_3, COURT_ID_4)
                .containsSubsequence(COURT_ID_1, COURT_ID_2, COURT_ID_3, COURT_ID_0);
    }

    @Test
    void testUniqueNameIsEnforced() {
        CourtEntity duplicate = this.newEntity();
        duplicate.setName(COURT_0.getName());
        assertThatThrownBy(() -> this.courtRepository.saveAndFlush(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testUniquePhoneIsEnforced() {
        CourtEntity duplicate = this.newEntity();
        duplicate.setPhone(COURT_0.getPhone());
        assertThatThrownBy(() -> this.courtRepository.saveAndFlush(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testNullPhoneIsAllowedTwice() {
        CourtEntity first = this.newEntity();
        CourtEntity second = this.newEntity();
        this.courtRepository.saveAndFlush(first);
        this.courtRepository.saveAndFlush(second);
        assertThat(this.courtRepository.existsById(first.getId())).isTrue();
        assertThat(this.courtRepository.existsById(second.getId())).isTrue();
    }

    @Test
    void testExistsByCourtId() {
        CourtEntity court = this.courtRepository.saveAndFlush(this.newEntity());
        assertThat(this.courtHearingRepository.existsByCourtId(court.getId())).isFalse();
        this.courtHearingRepository.saveAndFlush(CourtHearingEntity.builder().id(UUID.randomUUID())
                .date(LocalDateTime.of(2026, 1, 1, 10, 0)).roomNumber("A1")
                .openToPublic(false).remote(false)
                .type(CourtHearingType.TRIAL).status(CourtHearingStatus.SCHEDULED)
                .court(court).attendeeIds(Set.of(UUID.randomUUID())).build());
        assertThat(this.courtHearingRepository.existsByCourtId(court.getId())).isTrue();
    }

    private CourtEntity newEntity() {
        return new CourtEntity(Court.builder().id(UUID.randomUUID())
                .name("Repository court " + UUID.randomUUID()).address("Address").city("City")
                .type(CourtType.CIVIL).build());
    }
}