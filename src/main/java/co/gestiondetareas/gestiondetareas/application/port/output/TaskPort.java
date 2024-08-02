package co.gestiondetareas.gestiondetareas.application.port.output;

import co.gestiondetareas.gestiondetareas.domain.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskPort {
    Mono<Task> addTask(Task task);
    Mono<Task> updateTask(Task task);
    Mono<String> deleteTask(Long id);
    Flux<Task> getAllTasks();
    Mono<Task> getTaskById(Long id);
}
