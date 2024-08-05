package co.gestiondetareas.gestiondetareas.domain.dtos;

public record DeleteTaskFromUserDTO(
        Long idAdmin,
        Long idUser,
        Long idTask
) {
}
