package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.repositories;

import co.gestiondetareas.gestiondetareas.domain.model.User;
import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
    Mono<UserEntity> findByEmail(String email);
}
