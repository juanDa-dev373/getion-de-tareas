package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("users")
public class UserEntity {
    @Id @Column("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column("name") private String name;
    @Column("lastName") private String lastName;
    @Column("role") private String role;
    @Column("age") private int age;
    @Column("email") private String email;
    @Column("password") private String password;
}
