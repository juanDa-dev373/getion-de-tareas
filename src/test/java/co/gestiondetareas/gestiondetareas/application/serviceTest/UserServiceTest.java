package co.gestiondetareas.gestiondetareas.application.serviceTest;

import co.gestiondetareas.gestiondetareas.application.port.input.UserService;
import co.gestiondetareas.gestiondetareas.domain.dtos.LoginDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.PasswordRecovery;
import co.gestiondetareas.gestiondetareas.domain.dtos.RegisterUserDTO;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void registerUser() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                "David",
                "Fonseca",
                21,
                "admin",
                "juan2@gmail.com",
                "1234"
        );
        Mono<User> mono = userService.registerUser(registerUserDTO);
        Assertions.assertEquals(registerUserDTO.email(), mono.block().getEmail());
    }
    @Test
    public void loginTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO("juan1@gmail.com", "1234");
        Mono<User> mono = userService.login(loginDTO);
        Assertions.assertEquals(loginDTO.email(), mono.block().getEmail());
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

}