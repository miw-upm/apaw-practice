package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearingStatus;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import es.upm.miw.apaw.domain.model.courthearing.CourtHearing;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import org.springframework.beans.BeanUtils;
import java.util.HashSet;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourtHearingEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "hearing_date", nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String roomNumber;

    @Column(columnDefinition = "TEXT")
    private String transcript;

    private Integer durationMinutes;

    @Column(nullable = false)
    private Boolean openToPublic;

    @Column(nullable = false)
    private Boolean remote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourtHearingType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourtHearingStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "court_id", nullable = false)
    private CourtEntity court;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "court_hearing_attendee", joinColumns = @JoinColumn(name = "court_hearing_id"))
    @Column(name = "user_id", nullable = false)
    private Set<UUID> attendeeIds;

    public CourtHearingEntity(CourtHearing courtHearing) {
        BeanUtils.copyProperties(courtHearing, this, "attendees");
        this.attendeeIds = courtHearing.getAttendees().stream()
                .map(UserSnapshot::getId)
                .collect(Collectors.toCollection(HashSet::new));
    }
}