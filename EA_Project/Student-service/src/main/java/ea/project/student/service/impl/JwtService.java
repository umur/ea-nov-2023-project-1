package ea.project.student.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public void validateToken(final String token) {
        System.out.println("-- ValidateToken  \n" +token);
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        System.out.println("-- Token Validated ");
    }

    public static String getTokenTokenHeader() {
       String token=  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            System.out.println(token);
            return token;
        }

        return "NA";
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public boolean hasRole(String token,String role)
    {
        System.out.println("Check role ");
        Claims body = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
        // Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token)

        System.out.println("required  role :" +  role);
        Object o = body.get("role");
        System.out.println("user role :" +  o);

        if(o!=null && String.valueOf(o).equals(role))
        {
            System.out.println("Authorized " + role );
            return true;}

        return false;


    }

    public Long getUserIdFromToken()
    {
        String token=JwtService.getTokenTokenHeader();
        Claims body = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();

        Object id = body.get("id");
         if(id!=null)
          return Long.parseLong(id.toString());
         return null;



    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}