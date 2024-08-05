package co.gestiondetareas.gestiondetareas.application.port.input;

import co.gestiondetareas.gestiondetareas.domain.dtos.*;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> registerUser(RegisterUserDTO registerUserDTO) throws Exception;
    Mono<User> login(LoginDTO loginDTO) throws Exception;
    Mono<User> recoveryPassword(PasswordRecovery passwordRecovery) throws Exception;
    Mono<String> addTaskToUser(AddTaskToUserDTO addTaskToUserDTO) throws Exception;
    Mono<String> deleteTaskFromUser(DeleteTaskFromUserDTO deleteTaskFromUserDTO) throws Exception;
}
