package co.gestiondetareas.gestiondetareas.infrastructure.adapters.output.JWT;

import co.gestiondetareas.gestiondetareas.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
@Component
public class Jwt {
    private String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecretsecretsecret";
    public Mono<String> token(User user){
        return Mono.just(Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("lastName", user.getLastName())
                .claim("role", user.getRole())
                .claim("id", user.getId())
                .expiration(new Date(System.currentTimeMillis()+(1000*60*60)))
                .compact());
    }
    public Mono<String> getRole(String token)throws Exception{
        Claims claim = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        if(isExpired(token)){
            throw new Exception("Token expired");
        }
        return Mono.just(claim.get("role", String.class));
    }
    public Mono<String> getEmail(String token) throws Exception{
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                .build().parseSignedClaims(token)
                .getPayload();
        if (isExpired(token)){
            throw new Exception("Token expired");
        }
        return Mono.just(claims.get("email", String.class));
    }

    public Mono<String> getId(String token) throws Exception {
        Claims claims = getClaims(token);
        System.out.println(new Date());
        if(isExpired(token)){
            throw new Exception("Token expired");
        }
        return Mono.just(claims.get("id", String.class));
    }
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .build().parseSignedClaims(token)
                .getPayload();
    }
    private boolean isExpired(String token) throws Exception{
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date());
    }


}
