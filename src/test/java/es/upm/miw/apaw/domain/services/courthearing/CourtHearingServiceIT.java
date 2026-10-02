package es.upm.miw.apaw.domain.services.courthearing;

import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtHearingEntity;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtHearingRepository;
import es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.courthearing.*;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.COURT_ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CourtHearingServiceIT {
    private static final LocalDateTime DATE = LocalDateTime.of(2030, 5, 20, 10, 0);

    @Autowired
    private CourtHearingService courtHearingService;
    @Autowired
    private CourtHearingRepository courtHearingRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot first = this.user("0000", "600000100", "cliente0");
        UserSnapshot second = this.user("0001", "600000101", "cliente1");
        CreationCourtHearing creation = this.creation(COURT_ID_0, List.of(first.getId(), second.getId()));
        when(this.userFinder.findByIds(Set.of(first.getId(), second.getId())))
                .thenReturn(List.of(first, second));

        CourtHearing courtHearing = this.courtHearingService.create(creation);

        assertThat(courtHearing.getId()).isNotNull();
        assertThat(courtHearing.getStatus()).isEqualTo(CourtHearingStatus.SCHEDULED);
        assertThat(courtHearing.getOpenToPublic()).isFalse();
        assertThat(courtHearing.getRemote()).isFalse();
        assertThat(courtHearing.getDate()).isEqualTo(DATE);
        assertThat(courtHearing.getRoomNumber()).isEqualTo("A-101");
        assertThat(courtHearing.getType()).isEqualTo(CourtHearingType.TRIAL);
        assertThat(courtHearing.getAttendees()).containsExactlyInAnyOrder(first, second);
        this.entityManager.flush();
        this.entityManager.clear();
        CourtHearingEntity entity = this.courtHearingRepository.findById(courtHearing.getId()).orElseThrow();
        assertThat(entity.getRoomNumber()).isEqualTo("A-101");
        assertThat(entity.getStatus()).isEqualTo(CourtHearingStatus.SCHEDULED);
        assertThat(entity.getCourt().getId()).isEqualTo(COURT_ID_0);
        assertThat(entity.getAttendeeIds()).containsExactlyInAnyOrder(first.getId(), second.getId());
    }

    @Test
    @Transactional
    void testCreateKeepsInformedFlags() {
        UserSnapshot user = this.user("0000", "600000100", "cliente0");
        CreationCourtHearing creation = this.creation(COURT_ID_0, List.of(user.getId()));
        creation.setOpenToPublic(true);
        creation.setRemote(true);
        when(this.userFinder.findByIds(Set.of(user.getId()))).thenReturn(List.of(user));

        CourtHearing courtHearing = this.courtHearingService.create(creation);

        assertThat(courtHearing.getOpenToPublic()).isTrue();
        assertThat(courtHearing.getRemote()).isTrue();
        this.entityManager.flush();
        this.entityManager.clear();
        CourtHearingEntity entity = this.courtHearingRepository.findById(courtHearing.getId()).orElseThrow();
        assertThat(entity.getOpenToPublic()).isTrue();
        assertThat(entity.getRemote()).isTrue();
    }

    @Test
    @Transactional
    void testCreateCourtNotFound() {
        UUID unknownCourtId = UUID.randomUUID();
        CreationCourtHearing creation = this.creation(unknownCourtId, List.of(UUID.randomUUID()));
        long before = this.courtHearingRepository.count();

        assertThatThrownBy(() -> this.courtHearingService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(unknownCourtId.toString());
        verifyNoInteractions(this.userFinder);
        assertThat(this.courtHearingRepository.count()).isEqualTo(before);
    }

    @Test
    @Transactional
    void testCreateAttendeeNotFound() {
        UserSnapshot existing = this.user("0000", "600000100", "cliente0");
        UUID missingId = UUID.randomUUID();
        CreationCourtHearing creation = this.creation(COURT_ID_0, List.of(existing.getId(), missingId));
        when(this.userFinder.findByIds(Set.of(existing.getId(), missingId))).thenReturn(List.of(existing));
        long before = this.courtHearingRepository.count();

        assertThatThrownBy(() -> this.courtHearingService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingId.toString())
                .hasMessageNotContaining(existing.getId().toString());
        assertThat(this.courtHearingRepository.count()).isEqualTo(before);
    }

    private CreationCourtHearing creation(UUID courtId, List<UUID> attendeeIds) {
        return CreationCourtHearing.builder()
                .date(DATE)
                .roomNumber("A-101")
                .durationMinutes(90)
                .type(CourtHearingType.TRIAL)
                .courtId(courtId)
                .attendeeIds(attendeeIds)
                .build();
    }

    private UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff" + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Test
    @Transactional
    void testFindByUserMobile() {
        UserSnapshot firstUser = this.user("0000", "600000100", "cliente0");
        UserSnapshot secondUser = this.user("0001", "600000101", "cliente1");

        when(this.userFinder.findByIds(anySet()))
                .thenReturn(List.of(firstUser, secondUser));

        List<CourtHearing> courtHearings = this.courtHearingService.find(
                CourtHearingFindCriteria.builder()
                        .date(LocalDate.of(2025, 3, 12))
                        .userMobile(firstUser.getMobile())
                        .build());

        assertThat(courtHearings)
                .extracting(CourtHearing::getId)
                .containsExactly(CourtHearingSeederForDev.HEARING_ID_0);

        assertThat(courtHearings.getFirst().getAttendees())
                .extracting(UserSnapshot::getId)
                .containsExactlyInAnyOrder(
                        firstUser.getId(),
                        secondUser.getId());
    }



    @Test
    @Transactional
    void testFindByDate() {
        UserSnapshot firstUser = this.user("0000", "600000100", "cliente0");
        UserSnapshot thirdUser = this.user("0002", "600000102", "cliente2");

        when(this.userFinder.findByIds(Set.of(firstUser.getId(), thirdUser.getId())))
                .thenReturn(List.of(firstUser, thirdUser));

        List<CourtHearing> courtHearings = this.courtHearingService.find(
                CourtHearingFindCriteria.builder()
                        .date(LocalDate.of(2026, 11, 4))
                        .build());

        assertThat(courtHearings)
                .extracting(CourtHearing::getId)
                .containsExactly(CourtHearingSeederForDev.HEARING_ID_1);

        assertThat(courtHearings.getFirst().getAttendees())
                .extracting(UserSnapshot::getId)
                .containsExactlyInAnyOrder(firstUser.getId(), thirdUser.getId());
    }

    @Test
    @Transactional
    void testFindByScheduled() {
        UserSnapshot firstUser = this.user("0000", "600000100", "cliente0");
        UserSnapshot thirdUser = this.user("0002", "600000102", "cliente2");

        when(this.userFinder.findByIds(anySet()))
                .thenReturn(List.of(firstUser, thirdUser));

        List<CourtHearing> courtHearings = this.courtHearingService.find(
                CourtHearingFindCriteria.builder()
                        .date(LocalDate.of(2026, 11, 4))
                        .scheduled(true)
                        .build());

        assertThat(courtHearings)
                .extracting(CourtHearing::getId)
                .containsExactly(CourtHearingSeederForDev.HEARING_ID_1);

        assertThat(courtHearings.getFirst().getAttendees())
                .extracting(UserSnapshot::getId)
                .containsExactlyInAnyOrder(
                        firstUser.getId(),
                        thirdUser.getId());
    }



    @Test
    @Transactional
    void testFindByNotScheduled() {
        UserSnapshot firstUser = this.user("0000", "600000100", "cliente0");
        UserSnapshot secondUser = this.user("0001", "600000101", "cliente1");
        UserSnapshot thirdUser = this.user("0002", "600000102", "cliente2");
        UserSnapshot fourthUser = this.user("0003", "600000103", "cliente3");
        UserSnapshot fifthUser = this.user("0004", "600000104", "cliente4");

        when(this.userFinder.findByIds(anySet()))
                .thenReturn(List.of(firstUser, secondUser, thirdUser, fourthUser, fifthUser));

        List<CourtHearing> courtHearings = this.courtHearingService.find(
                CourtHearingFindCriteria.builder()
                        .scheduled(false)
                        .build());

        assertThat(courtHearings)
                .extracting(CourtHearing::getId)
                .containsExactly(
                        CourtHearingSeederForDev.HEARING_ID_0,
                        CourtHearingSeederForDev.HEARING_ID_3,
                        CourtHearingSeederForDev.HEARING_ID_6,
                        CourtHearingSeederForDev.HEARING_ID_5);
    }


    @Test
    @Transactional
    void testFindByCourtCity() {
        UserSnapshot thirdUser = this.user("0002", "600000102", "cliente2");
        UserSnapshot fourthUser = this.user("0003", "600000103", "cliente3");
        UserSnapshot fifthUser = this.user("0004", "600000104", "cliente4");

        when(this.userFinder.findByIds(Set.of(
                thirdUser.getId(),
                fourthUser.getId(),
                fifthUser.getId())))
                .thenReturn(List.of(thirdUser, fourthUser, fifthUser));

        List<CourtHearing> courtHearings = this.courtHearingService.find(
                CourtHearingFindCriteria.builder()
                        .courtCity("Barcelona")
                        .build());

        assertThat(courtHearings)
                .extracting(CourtHearing::getId)
                .containsExactly(
                        CourtHearingSeederForDev.HEARING_ID_3,
                        CourtHearingSeederForDev.HEARING_ID_4);
    }

    @Test
    @Transactional
    void testFindWithoutResults() {
        List<CourtHearing> courtHearings = this.courtHearingService.find(
                CourtHearingFindCriteria.builder()
                        .date(LocalDate.of(2030, 5, 20))
                        .build());

        assertThat(courtHearings).isEmpty();
        verifyNoInteractions(this.userFinder);
    }

}