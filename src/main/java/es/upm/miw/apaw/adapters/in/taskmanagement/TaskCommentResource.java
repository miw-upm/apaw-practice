package es.upm.miw.apaw.adapters.in.taskmanagement;

import es.upm.miw.apaw.domain.model.taskmanagement.TaskComment;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskCommentUpdate;
import es.upm.miw.apaw.domain.services.taskmanagement.TaskCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(TaskCommentResource.TASK_COMMENTS)
@RequiredArgsConstructor
public class TaskCommentResource {

    public static final String TASK_COMMENTS = "/task-comments";
    public static final String ID = "/{id}";

    private final TaskCommentService taskCommentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskComment create(
            @Valid @RequestBody TaskComment taskComment) {
        return this.taskCommentService.create(taskComment);
    }

    @GetMapping(ID)
    public TaskComment read(@PathVariable UUID id) {
        return this.taskCommentService.read(id);
    }

    @PutMapping(ID)
    public TaskComment update(
            @PathVariable UUID id,
            @Valid @RequestBody TaskComment taskComment) {
        return this.taskCommentService.update(id, taskComment);
    }

    @PatchMapping(ID)
    public TaskComment update(
            @PathVariable UUID id,
            @Valid @RequestBody TaskCommentUpdate update) {
        return this.taskCommentService.update(id, update);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.taskCommentService.delete(id);
    }

    @GetMapping
    public List<TaskComment> findAll() {
        return this.taskCommentService.findAll();
    }
}
