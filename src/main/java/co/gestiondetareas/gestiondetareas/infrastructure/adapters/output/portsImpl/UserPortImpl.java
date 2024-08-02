package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.portsImpl;

import co.gestiondetareas.gestiondetareas.application.port.output.UserPort;
import co.gestiondetareas.gestiondetareas.domain.model.User;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities.UserEntity;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.repositories.UserRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserPortImpl implements UserPort {
    private final UserRepository userRepository;
    public UserPortImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Mono<User> findById(Long id) {
        return userRepository.findById(id).map(this::toUser);
    }

    @Override
    public Mono<User> save(User user) {
        System.out.println("entro: "+user.getEmail());
        return userRepository.save(toUserEntity(user)).map(this::toUser);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toUser);
    }

    private User toUser(UserEntity userEntity) {
        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());
        user.setPassword(userEntity.getPassword());
        user.setAge(userEntity.getAge());
        user.setId(userEntity.getId());
        user.setRole(userEntity.getRole());
        user.setLastName(userEntity.getLastName());
        return user;
    }
    private UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setAge(user.getAge());
        userEntity.setRole(user.getRole());
        userEntity.setLastName(user.getLastName());
        return userEntity;
    }
}
