package co.gestiondetareas.gestiondetareas.domain.dtos;

public record RegisterUserDTO(
        String name,
        String lastName,
        int age,
        String role,
        String email,
        String password
) {
}
