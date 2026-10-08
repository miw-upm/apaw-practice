package es.upm.miw.apaw.domain.ports.out.taskmanagement;

import es.upm.miw.apaw.domain.model.taskmanagement.Task;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskActivityReport;

import java.util.List;

public interface TaskGateway {

    Task create(Task task);

    boolean existsByTitle(String title);

    List<TaskActivityReport> findActivityReport();
}
