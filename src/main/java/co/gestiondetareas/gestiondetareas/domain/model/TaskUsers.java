package co.gestiondetareas.gestiondetareas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskUsers {
    private Long idTask;
    private Long idUser;
}
