package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.DeadlineGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DeadlineAdapter implements DeadlineGateway {
    private final DeadlineRepository deadlineRepository;
    private final NonWorkingDayRepository nonWorkingDayRepository;

    @Override
    @Transactional
    public Deadline create(Deadline deadline) {
        DeadlineEntity deadlineEntity = new DeadlineEntity(deadline);
        List<NonWorkingDayEntity> nonWorkingDayEntities = deadline.getNonWorkingDays().stream()
                .map(nonWorkingDay -> this.nonWorkingDayRepository.getReferenceById(nonWorkingDay.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        deadlineEntity.setNonWorkingDays(nonWorkingDayEntities);
        this.deadlineRepository.save(deadlineEntity);
        return deadline;
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.deadlineRepository.existsByTitle(title);
    }

    @Override
    public List<DeadlineWorkloadReport> findWorkloadReport(LocalDate today) {
        return this.deadlineRepository.findWorkloadReport(today);
    }
}
