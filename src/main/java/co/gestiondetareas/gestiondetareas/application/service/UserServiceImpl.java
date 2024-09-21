package co.gestiondetareas.gestiondetareas.application.service;

import co.gestiondetareas.gestiondetareas.application.port.input.UserService;
import co.gestiondetareas.gestiondetareas.application.port.output.TaskPort;
import co.gestiondetareas.gestiondetareas.application.port.output.TaskUsersPort;
import co.gestiondetareas.gestiondetareas.application.port.output.UserPort;
import co.gestiondetareas.gestiondetareas.domain.dtos.*;
import co.gestiondetareas.gestiondetareas.domain.model.Task;
import co.gestiondetareas.gestiondetareas.domain.model.TaskUsers;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.JWT.Jwt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UserServiceImpl implements UserService {
    private final UserPort userPort;
    private final TaskPort taskPort;
    private final TaskUsersPort taskUsersPort;
    private final Jwt jwt;
    public UserServiceImpl(UserPort userPort, TaskPort taskPort, TaskUsersPort taskUsersPort, Jwt jwt) {
        this.userPort = userPort;
        this.taskPort = taskPort;
        this.taskUsersPort = taskUsersPort;
        this.jwt = jwt;
    }

    @Override
    public Mono<User> registerUser(RegisterUserDTO registerUserDTO) throws Exception {
        User user = new User();
        user.setLastName(registerUserDTO.lastName());
        user.setRole("client");
        user.setPassword(encoderPassword(registerUserDTO.password()));
        user.setEmail(registerUserDTO.email());
        user.setAge(registerUserDTO.age());
        user.setName(registerUserDTO.name());
        return userPort.findByEmail(registerUserDTO.email()).flatMap(
                u->Mono.error(new Exception("the email was already in use"))
        ).then(userPort.save(user));
    }

    @Override
    public Mono<String> login(LoginDTO loginDTO) throws Exception {
        return userPort.findByEmail(loginDTO.email()).flatMap(
                u ->{
                    if(!isCorrectPassword(loginDTO.password(), u.getPassword())){
                        return Mono.error(new Exception("the password or email was incorrect"));
                    }
                    return jwt.token(u);
                }
        ).switchIfEmpty(
                Mono.error(new Exception("The user wasn´t found"))
        );
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
    public Mono<String> addTaskToUser(AddTaskToUserDTO addTaskToUserDTO) {
        return taskPort.getTaskById(addTaskToUserDTO.idTask())
                .switchIfEmpty(Mono.error(new Exception("The task does not exist")))
                .zipWith(userPort.findById(addTaskToUserDTO.idUser())
                        .switchIfEmpty(Mono.error(new Exception("The user does not exist"))))
                .zipWith(userPort.findById(addTaskToUserDTO.idAdmin())
                        .switchIfEmpty(Mono.error(new Exception("The admin does not exist"))))
                .flatMap(tuple -> {
                    Task task = tuple.getT1().getT1();
                    User user = tuple.getT1().getT2();
                    User admin = tuple.getT2();
                    if ("client".equals(admin.getRole())) {
                        return Mono.error(new Exception("The user is not admin"));
                    }

                    TaskUsers taskUsers = new TaskUsers(task.getId(), user.getId());
                    return taskUsersPort.save(taskUsers)
                            .thenReturn("Task successfully added to user");
                });
    }

    @Override
    public Mono<String> deleteTaskFromUser(DeleteTaskFromUserDTO deleteTaskFromUserDTO) throws Exception {
        TaskUsers taskUsers = new TaskUsers(deleteTaskFromUserDTO.idTask(), deleteTaskFromUserDTO.idUser());
        return taskUsersPort.delete(taskUsers);
    }
    private String encoderPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    private boolean isCorrectPassword(String password, String passwordEncode){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, passwordEncode);
    }
}
