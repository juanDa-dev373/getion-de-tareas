package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("taskUser")
public class TaskUsersEntity {
    @Column("idTask") private Long idTask;
    @Column("idUser") private Long idUser;
}
