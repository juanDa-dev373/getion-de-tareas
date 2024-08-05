package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.portsImpl;

import co.gestiondetareas.gestiondetareas.application.port.output.TaskUsersPort;
import co.gestiondetareas.gestiondetareas.domain.model.TaskUsers;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities.TaskUsersEntity;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.repositories.TaskUsersRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class TaskUsersPortImpl implements TaskUsersPort {
    private final TaskUsersRepository taskUsersRepository;

    public TaskUsersPortImpl(TaskUsersRepository taskUsersRepository) {
        this.taskUsersRepository = taskUsersRepository;
    }

    @Override
    public Mono<String> save(TaskUsers taskUsers) {
        return taskUsersRepository.save(ToTaskUsersEntity(taskUsers)).then(Mono.just("the task was registered successfully to the user"));
    }

    @Override
    public Mono<String> delete(TaskUsers taskUsers) {
        return taskUsersRepository.deleteTaskUsersEntitiesByIdTaskAndIdUser(taskUsers.getIdTask(),taskUsers.getIdUser()).then(Mono.just("the task was deleted from the user"));
    }
    private TaskUsersEntity ToTaskUsersEntity(TaskUsers taskUsers) {
        TaskUsersEntity taskUsersEntity = new TaskUsersEntity();
        taskUsersEntity.setIdTask(taskUsers.getIdTask());
        taskUsersEntity.setIdUser(taskUsers.getIdUser());
        return taskUsersEntity;
    }
}
