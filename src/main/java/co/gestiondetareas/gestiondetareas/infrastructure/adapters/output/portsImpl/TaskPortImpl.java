package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.portsImpl;

import co.gestiondetareas.gestiondetareas.application.port.output.TaskPort;
import co.gestiondetareas.gestiondetareas.domain.model.Task;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities.TaskEntity;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.repositories.TaskRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TaskPortImpl implements TaskPort {
    private final TaskRepository taskRepository;
    public TaskPortImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Mono<Task> addTask(Task task) {
        System.out.println("entro"+toTaskEntity(task).getTitle());
        return taskRepository.save(toTaskEntity(task)).map(this::toTaskModel);
    }

    @Override
    public Mono<Task> updateTask(Task task) {
        return taskRepository.save(toTaskEntity(task)).map(this::toTaskModel);
    }

    @Override
    public Mono<String> deleteTask(Long id) {
        return taskRepository.deleteById(id).then(Mono.just("The task was elimited"));
    }

    @Override
    public Flux<Task> getAllTasks() {
        return taskRepository.findAll().map(this::toTaskModel);
    }

    @Override
    public Mono<Task> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::toTaskModel);
    }
    private Task toTaskModel(TaskEntity taskEntity){
        Task task = new Task();
        task.setId(taskEntity.getId());
        task.setDescription(taskEntity.getDescription());
        task.setTitle(taskEntity.getTitle());
        task.setDueDate(taskEntity.getDueDate());
        return task;
    }
    private TaskEntity toTaskEntity(Task task){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(task.getId());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDueDate(task.getDueDate());
        return taskEntity;
    }
}
