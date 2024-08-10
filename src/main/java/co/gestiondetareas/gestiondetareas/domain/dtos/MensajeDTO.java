package co.gestiondetareas.gestiondetareas.domain.dtos;

public record MensajeDTO<T>(
        Boolean error,
        T response
){
}
