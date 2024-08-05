package co.gestiondetareas.gestiondetareas.application.port.output;

import co.gestiondetareas.gestiondetareas.domain.model.TaskUsers;
import reactor.core.publisher.Mono;

public interface TaskUsersPort {
    Mono<String> save(TaskUsers taskUsers);
    Mono<String> delete(TaskUsers taskUsers);
}
