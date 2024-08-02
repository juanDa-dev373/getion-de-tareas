package co.gestiondetareas.gestiondetareas.application.service;

import co.gestiondetareas.gestiondetareas.application.port.input.TaskService;
import co.gestiondetareas.gestiondetareas.application.port.output.TaskPort;
import co.gestiondetareas.gestiondetareas.domain.dtos.CreateTaskDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.UpdateTaskDTO;
import co.gestiondetareas.gestiondetareas.domain.model.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class TaskServiceImpl implements TaskService {
    
    private final TaskPort taskPort;
    public TaskServiceImpl(TaskPort taskPort) {
        this.taskPort = taskPort;
    }

    @Override
    public Mono<Task> addTask(CreateTaskDTO createTaskDTO) throws Exception {
        Task task = new Task();
        task.setDescription(createTaskDTO.description());
        task.setTitle(createTaskDTO.title());
        task.setDueDate(createTaskDTO.dueDate());
        return taskPort.addTask(task);
    }

    @Override
    public Mono<Task> updateTask(UpdateTaskDTO updateTaskDTO) throws Exception {
        return taskPort.getTaskById(updateTaskDTO.id()).switchIfEmpty(
                Mono.error(new Exception("The task is not found"))
        ).flatMap(
                task -> {
                    task.setDescription(updateTaskDTO.description());
                    task.setTitle(updateTaskDTO.title());
                    task.setDueDate(updateTaskDTO.dueDate());
                    return taskPort.updateTask(task);
                }
        );
    }

    @Override
    public Mono<String> deleteTask(Long id) throws Exception {
        return taskPort.getTaskById(id).switchIfEmpty(
                Mono.error(new Exception("The task is not found"))
        ).then(taskPort.deleteTask(id));
    }

    @Override
    public Flux<Task> getAllTasks() throws Exception {
        return taskPort.getAllTasks();
    }
}
