package co.gestiondetareas.gestiondetareas.domain.dtos;

public record AddTaskToUserDTO(
        Long idAdmin,
        Long idUser,
        Long idTask
) {
}
