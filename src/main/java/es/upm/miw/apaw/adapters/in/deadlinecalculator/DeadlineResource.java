package es.upm.miw.apaw.adapters.in.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.CreationDeadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineFindCriteria;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;
import es.upm.miw.apaw.domain.services.deadlinecalculator.DeadlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DeadlineResource.DEADLINES)
@RequiredArgsConstructor
public class DeadlineResource {
    public static final String DEADLINES = "/deadlines";
    public static final String REPORT = "/report";

    private final DeadlineService deadlineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Deadline create(@Valid @RequestBody CreationDeadline creation) {
        return this.deadlineService.create(creation);
    }

    @GetMapping(REPORT)
    public List<DeadlineWorkloadReport> findWorkloadReport() {
        return this.deadlineService.findWorkloadReport();
    }

    @GetMapping
    public List<Deadline> find(@ModelAttribute DeadlineFindCriteria criteria) {
        return this.deadlineService.find(criteria);
    }
}
