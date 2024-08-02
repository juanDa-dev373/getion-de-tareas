package co.gestiondetareas.gestiondetareas.application.service;

import co.gestiondetareas.gestiondetareas.application.port.input.UserService;
import co.gestiondetareas.gestiondetareas.application.port.output.UserPort;
import co.gestiondetareas.gestiondetareas.domain.dtos.AddTaskToUserDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.LoginDTO;
import co.gestiondetareas.gestiondetareas.domain.dtos.PasswordRecovery;
import co.gestiondetareas.gestiondetareas.domain.dtos.RegisterUserDTO;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UserServiceImpl implements UserService {
    private final UserPort userPort;

    public UserServiceImpl(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public Mono<User> registerUser(RegisterUserDTO registerUserDTO) throws Exception {
        User user = new User();
        user.setLastName(registerUserDTO.lastName());
        user.setRole(registerUserDTO.role());
        user.setPassword(registerUserDTO.password());
        user.setEmail(registerUserDTO.email());
        user.setAge(registerUserDTO.age());
        user.setName(registerUserDTO.name());
        return userPort.findByEmail(registerUserDTO.email()).flatMap(
                u -> Mono.error(new Exception("the email was already in use"))
        ).then(userPort.save(user));
    }

    @Override
    public Mono<User> login(LoginDTO loginDTO) throws Exception {
        return userPort.findByEmail(loginDTO.email()).filter(
                u-> loginDTO.password().equals(u.getPassword())
        ).switchIfEmpty(
                Mono.error(new Exception("The password or email invalidated"))
        );
    }

    @Override
    public Mono<String> signOff(Long id) throws Exception {
        return null;
    }

    @Override
    public Mono<User> recoveryPassword(PasswordRecovery passwordRecovery) throws Exception {
        return userPort.findByEmail(passwordRecovery.email()).flatMap(
                u-> {
                    u.setPassword(passwordRecovery.newPassword());
                    return userPort.save(u);
                }
        ).switchIfEmpty(
                Mono.error(new Exception("The email not valid"))
        );
    }

    @Override
    public Mono<String> addTaskToUser(AddTaskToUserDTO addTaskToUserDTO) throws Exception {

        return null;
    }

    @Override
    public Mono<String> deleteTaskFromUser(Long id, Long taskId) throws Exception {
        return null;
    }
}
