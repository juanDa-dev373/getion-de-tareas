package co.gestiondetareas.gestiondetareas.infrastructure.adapters.jwtService;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    public String generateToken(String email, Map<String, Object> claims){
        Instant now = Instant.now();
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1L, ChronoUnit.HOURS)))
                .signWith(getKey())
                .compact();
    }
    public String generatePasswordToken(String email, Map<String, Object> claims){

        Instant now = Instant.now();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1L, ChronoUnit.MINUTES)))
                .signWith( getKey() )
                .compact();

    }
    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith(getKey()).build();
        return jwtParser.parseSignedClaims(jwtString);
    }

    private SecretKey getKey(){
        String secretKey = "secretsecretsecretsecretsecretsecretsecretsecret";
        byte[] secretKeyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
