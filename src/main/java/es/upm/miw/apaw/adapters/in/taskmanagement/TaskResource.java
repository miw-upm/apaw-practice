package es.upm.miw.apaw.adapters.in.taskmanagement;

import es.upm.miw.apaw.domain.model.taskmanagement.CreationTask;
import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.services.taskmanagement.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TaskResource.TASKS)
@RequiredArgsConstructor
public class TaskResource {

    public static final String TASKS = "/tasks";

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody CreationTask creation) {
        return this.taskService.create(creation);
    }
}
