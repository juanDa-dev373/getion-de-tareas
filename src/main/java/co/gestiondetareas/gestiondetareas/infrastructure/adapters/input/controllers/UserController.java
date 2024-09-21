package co.gestiondetareas.gestiondetareas.infrastructure.adapters.input.controllers;


import co.gestiondetareas.gestiondetareas.application.port.input.UserService;
import co.gestiondetareas.gestiondetareas.domain.dtos.MensajeDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.RegisterUserDTO;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public MensajeDTO<Mono<User>> login(@RequestBody RegisterUserDTO registerUserDTO) {
        return null;
    }
    @PostMapping("/register-user")
    public Mono<User> registrerUser(@RequestBody RegisterUserDTO registerUserDTO) throws Exception {
        return userService.registerUser(registerUserDTO);
    }


}
