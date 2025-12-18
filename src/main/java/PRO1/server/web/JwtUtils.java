package PRO1.server.web;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    // La clé doit être plus longue pour plus de sécurité (au moins 64 caractères)
    private String jwtSecret = "MaCleSecreteUltraSecuriseeQuiDoitFaireAuMoinsSoixanteQuatreCaracteres!";
    private int jwtExpirationMs = 86400000;

    public String generateToken(String email) {
        // On crée une clé sécurisée à partir de la chaîne
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key) // Utilise la clé directement
                .compact();
    }
}