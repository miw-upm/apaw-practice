package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.deadlinecalculator.CreationDeadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.DeadlineGateway;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.NonWorkingDayGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeadlineService {
    private final DeadlineGateway deadlineGateway;
    private final NonWorkingDayGateway nonWorkingDayGateway;
    private final UserFinder userFinder;

    public Deadline create(CreationDeadline creation) {
        if (this.deadlineGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Deadline title already exists: " + creation.getTitle());
        }
        Deadline deadline = new Deadline();
        BeanUtils.copyProperties(creation, deadline);
        deadline.doDefault();
        deadline.setNonWorkingDays(deadline.hasWorkingDayCount()
                ? this.nonWorkingDayGateway.findApplicable(creation.getRegion(), creation.getCity())
                : List.of());
        deadline.setUserSnapshot(this.userFinder.read(creation.getUserId()));
        deadline.doCalculate();
        return this.deadlineGateway.create(deadline);
    }
}
