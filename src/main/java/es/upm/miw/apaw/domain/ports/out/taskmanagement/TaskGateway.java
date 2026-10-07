package es.upm.miw.apaw.domain.ports.out.taskmanagement;

import es.upm.miw.apaw.domain.model.taskmanagement.Task;

public interface TaskGateway {

    Task create(Task task);

    boolean existsByTitle(String title);
}
