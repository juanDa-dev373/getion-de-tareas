package co.gestiondetareas.gestiondetareas.domain.dtos;

import java.time.LocalDateTime;

public record CreateTaskDTO(
        String title,
        String description,
        LocalDateTime dueDate
) {
}
