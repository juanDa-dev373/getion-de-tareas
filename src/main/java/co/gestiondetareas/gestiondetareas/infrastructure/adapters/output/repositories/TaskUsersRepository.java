package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.repositories;

import co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities.TaskUsersEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskUsersRepository extends R2dbcRepository<TaskUsersEntity, Long> {
}
