package co.gestiondetareas.gestiondetareas.infrastructure.adapters.jwtService;

import co.gestiondetareas.gestiondetareas.application.port.input.UserService;
import co.gestiondetareas.gestiondetareas.domain.dtos.MensajeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String requestURI = request.getRequestURI();
            String token = getToken(request);
            boolean error = true;
            try{
                if(requestURI.startsWith("/api/user")){
                    if(token != null){
                        Jws<Claims> jws = jwtUtil.parseJwt(token);
                        if(!jws.getPayload().get("rol").equals("admin")){
                            createRequestError("you don't have permision", 
                                    HttpServletResponse.SC_FORBIDDEN, response);
                        }else{
                            error=false;
                        }
                    }else{
                        createRequestError("",
                                HttpServletResponse.SC_FORBIDDEN, response);
                    }
                } else{
                    error = false;
                }
            }catch (MalformedJwtException e) {
                createRequestError("El token es incorrecto",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            } catch (ExpiredJwtException e) {
                createRequestError("El token esta vencido",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            } catch (Exception e) {
                createRequestError(e.getMessage(),
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            }
            if (!error) {
                filterChain.doFilter(request, response);
            }
        }

    }
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer", "");
        }
        return null;
    }
    private void createRequestError(String message, int errorCode, HttpServletResponse response) throws IOException {
        MensajeDTO<String> dto = new MensajeDTO<>(true, message);
        response.setContentType("application/json");
        response.setStatus(errorCode);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
