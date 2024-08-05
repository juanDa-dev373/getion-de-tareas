package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("taskuser")
public class TaskUsersEntity {
    @Column("idtask") private Long idTask;
    @Column("iduser") private Long idUser;
}
