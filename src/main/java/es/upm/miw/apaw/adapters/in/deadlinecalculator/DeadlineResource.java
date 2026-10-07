package es.upm.miw.apaw.adapters.in.deadlinecalculator;

import es.upm.miw.apaw.domain.model.deadlinecalculator.CreationDeadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.services.deadlinecalculator.DeadlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DeadlineResource.DEADLINES)
@RequiredArgsConstructor
public class DeadlineResource {
    public static final String DEADLINES = "/deadlines";

    private final DeadlineService deadlineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Deadline create(@Valid @RequestBody CreationDeadline creation) {
        return this.deadlineService.create(creation);
    }
}
