package co.gestiondetareas.gestiondetareas.application.port.output;

import co.gestiondetareas.gestiondetareas.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserPort {
    Mono<User> findById(Long id);
    Mono<User> save(User user);
    Mono<User> findByEmail(String email);
}
