package es.upm.miw.apaw.domain.services.judicialcourt;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.judicialcourt.CreationJudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourt;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtRankingReport;
import es.upm.miw.apaw.domain.model.judicialcourt.LawyerCourtStat;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtGateway;
import es.upm.miw.apaw.domain.ports.out.judicialcourt.JudicialCourtTypeGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudicialCourtService {
    private final JudicialCourtGateway judicialCourtGateway;
    private final JudicialCourtTypeGateway judicialCourtTypeGateway;
    private final UserFinder userFinder;

    @Transactional
    public JudicialCourt create(CreationJudicialCourt creation) {
        if (creation == null) {
            throw new BadRequestException("Judicial court creation cannot be null");
        }
        if (creation.getTypeId() == null) {
            throw new BadRequestException("Judicial court type id cannot be null");
        }
        Set<UUID> userIds = new HashSet<>();
        if (creation.getLawyerIds() != null) {
            userIds.addAll(creation.getLawyerIds());
        }

        if (!userIds.isEmpty()) {
            Set<UUID> foundUserIds = this.userFinder.findByIds(userIds).stream()
                    .map(UserSnapshot::getId)
                    .collect(Collectors.toSet());
            if (!foundUserIds.containsAll(userIds)) {
                Set<UUID> missingUserIds = new HashSet<>(userIds);
                missingUserIds.removeAll(foundUserIds);
                throw new NotFoundException("User ids not found: " + missingUserIds);
            }
        }

        JudicialCourtType judicialCourtType = this.judicialCourtTypeGateway.read(creation.getTypeId())
                .orElseThrow(() -> new NotFoundException("Judicial court type id not found: " + creation.getTypeId()));

        JudicialCourt judicialCourt = new JudicialCourt();
        BeanUtils.copyProperties(creation, judicialCourt, "typeId", "lawyerIds");
        judicialCourt.setType(judicialCourtType);
        if (creation.getLawyerIds() != null) {
            judicialCourt.setLawyers(creation.getLawyerIds().stream()
                    .map(id -> UserSnapshot.builder().id(id).build())
                    .toList());
        }
        judicialCourt.doDefault();
        return this.judicialCourtGateway.create(judicialCourt);
    }

    public List<LawyerCourtRankingReport> findLawyerCourtRanking() {
        List<LawyerCourtStat> stats = this.judicialCourtGateway.findLawyerCourtStats();
        if (stats.isEmpty()) {
            return List.of();
        }

        Set<UUID> userIds = stats.stream()
                .map(LawyerCourtStat::getUserId)
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));

        return stats.stream()
                .map(stat -> {
                    UserSnapshot lawyer = usersById.get(stat.getUserId());
                    if (lawyer == null) {
                        throw new NotFoundException("User id not found: " + stat.getUserId());
                    }
                    return LawyerCourtRankingReport.builder()
                            .lawyer(lawyer)
                            .totalJudicialCourts(stat.getTotalCourts())
                            .build();
                })
                .toList();
    }
}
