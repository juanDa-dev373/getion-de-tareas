package co.gestiondetareas.gestiondetareas.application.service;

import co.gestiondetareas.gestiondetareas.application.port.input.UserService;
import co.gestiondetareas.gestiondetareas.domain.dtos.*;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void registerUser() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                "David",
                "Fonseca",
                21,
                "juan8@gmail.com",
                "1234"
        );
        Mono<User> mono = userService.registerUser(registerUserDTO);
        Assertions.assertEquals(registerUserDTO.email(), mono.block().getEmail());
    }
    @Test
    public void loginTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO("juan8@gmail.com", "1234");
        Mono<String> mono = userService.login(loginDTO);
        System.out.println(mono.block());
        assertNotNull(mono.block());
    }
    @Test
    public void passwordRecovery() throws Exception {
        PasswordRecovery passwordRecovery = new PasswordRecovery(
                "juan1@gmail.com",
                "juan123"
        );
        Mono<User> userMono = userService.recoveryPassword(passwordRecovery);
        Assertions.assertEquals(passwordRecovery.newPassword(), userMono.block().getPassword());
    }
    @Test
    public void addTaskToUserTest() throws Exception {
        AddTaskToUserDTO addTaskToUserDTO = new AddTaskToUserDTO(
                4L,
                3L,
                2L
        );
        String answer = userService.addTaskToUser(addTaskToUserDTO).block();
        Assertions.assertEquals("Task successfully added to user", answer);
    }
    @Test
    public void deleteTaskFromUser() throws Exception {
        DeleteTaskFromUserDTO deleteTaskFromUserDTO = new DeleteTaskFromUserDTO(
                1L,
                3L,
                2L
        );
        String answer = userService.deleteTaskFromUser(deleteTaskFromUserDTO).block();
        Assertions.assertEquals("the task was deleted from the user", answer);
    }
}