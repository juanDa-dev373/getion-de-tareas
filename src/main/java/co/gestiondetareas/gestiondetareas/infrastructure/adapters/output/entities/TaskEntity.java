package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("task")
public class TaskEntity {
    @Id @Column("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column("title") private String title;
    @Column("description") private String description;
    @Column("dueDate") private LocalDateTime dueDate;
}
