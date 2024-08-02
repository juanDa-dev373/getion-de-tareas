package co.gestiondetareas.gestiondetareas.domain.dtos;

import java.time.LocalDateTime;

public record UpdateTaskDTO(
        Long id,
        String description,
        String title,
        LocalDateTime dueDate
) {
}
