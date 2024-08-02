package co.gestiondetareas.gestiondetareas.application.serviceTest;

import co.gestiondetareas.gestiondetareas.application.port.input.TaskService;
import co.gestiondetareas.gestiondetareas.domain.dtos.CreateTaskDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.UpdateTaskDTO;
import co.gestiondetareas.gestiondetareas.domain.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TaskTest {
    @Autowired
    TaskService taskService;
    @Test
    public void createTask() throws Exception {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO(
          "juan",
          "muy lindo", LocalDateTime.now()
        );
        Task task = taskService.addTask(createTaskDTO).block();
        Assertions.assertEquals(createTaskDTO.title(), task.getTitle());
    }
    @Test
    public void getAllTasks() throws Exception {
        Flux<Task> taskFlux = taskService.getAllTasks();
        Assertions.assertNotNull(taskFlux);
    }
    @Test
    public void updateTask() throws Exception {
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO(
                3L,
                "hola mundo",
                "rios",
                LocalDateTime.now()
        );
        Task task = taskService.updateTask(updateTaskDTO).block();
        Assertions.assertEquals(updateTaskDTO.title(), task.getTitle());
    }
    @Test
    public void deleteTask() throws Exception {
        Assertions.assertEquals("The task was elimited", taskService.deleteTask(1L).block());
    }
}
