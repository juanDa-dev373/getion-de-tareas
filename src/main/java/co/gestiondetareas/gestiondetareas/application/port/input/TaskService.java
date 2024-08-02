package co.gestiondetareas.gestiondetareas.application.port.input;

import co.gestiondetareas.gestiondetareas.domain.dtos.CreateTaskDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.UpdateTaskDTO;
import co.gestiondetareas.gestiondetareas.domain.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Mono<Task> addTask(CreateTaskDTO createTaskDTO) throws Exception;
    Mono<Task> updateTask(UpdateTaskDTO updateTaskDTO) throws Exception;
    Mono<String> deleteTask(Long id) throws Exception;
    Flux<Task> getAllTasks() throws Exception;
}
