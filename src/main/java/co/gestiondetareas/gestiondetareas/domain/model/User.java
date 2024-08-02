package co.gestiondetareas.gestiondetareas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String role;
    private int age;
    private String email;
    private String password;

}
