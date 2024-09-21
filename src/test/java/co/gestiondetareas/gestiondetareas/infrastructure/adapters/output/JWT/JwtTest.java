package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.JWT;

import co.gestiondetareas.gestiondetareas.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTest {
    @Test
    public void createToken() {
        Jwt jwt = new Jwt();
        User user = new User();
        user.setRole("ROLE_USER");
        user.setPassword("password");
        user.setLastName("test");
        user.setName("test");
        user.setEmail("test@test.com");
        assertNotNull(jwt.token(user));
        System.out.println(jwt.token(user).block());
    }
    @Test
    public void getRoleTest() throws Exception {
        Jwt jwt = new Jwt();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RAdGVzdC5jb20iLCJuYW1lIjoidGVzdCIsImxhc3ROYW1lIjoidGVzdCIsInJvbGUiOiJST0xFX1VTRVIiLCJleHAiOjE3MjY0NDM3ODd9.sm0YPjIJc3Gal0F5y5yRjdkil-TQ-zSoQfzpirEc_x0";
        assertEquals("ROLE_USER", jwt.getRole(token));
    }
    @Test
    public void getEmailTest() throws Exception {
        Jwt jwt = new Jwt();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RAdGVzdC5jb20iLCJuYW1lIjoidGVzdCIsImxhc3ROYW1lIjoidGVzdCIsInJvbGUiOiJST0xFX1VTRVIiLCJleHAiOjE3MjY0NTA5NTZ9.jxFJIBdj83kBTaGrC49MwX8LX7pLA5EEcwydA9t6x9c";
        assertNotNull(jwt.getEmail(token));
    }
    @Test
    public void getIdTest() throws Exception {
        Jwt jwt = new Jwt();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RAdGVzdC5jb20iLCJuYW1lIjoidGVzdCIsImxhc3ROYW1lIjoidGVzdCIsInJvbGUiOiJST0xFX1VTRVIiLCJleHAiOjE3MjY0NTA5NTZ9.jxFJIBdj83kBTaGrC49MwX8LX7pLA5EEcwydA9t6x9c";
        assertNotNull(jwt.getId(token));
    }
}