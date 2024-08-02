package co.gestiondetareas.gestiondetareas.domain.dtos;

public record PasswordRecovery(
        String email,
        String newPassword
) {
}
