package com.example.demo.JWTManager;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.data.util.Pair;

public class JWTManager {
    private final String SECRET = "secret";

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Pair<String, Boolean> verifyToken(String token) {
        try {
            System.out.println("\033[0;32m" + "\n\n\n" + token + "\n\n\n" + "\033[0m");
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            String subject = jwt.getSubject();
            return Pair.of(subject, true);
        } catch (TokenExpiredException exception) {

        } catch (JWTVerificationException exception) {
           
        }
        return Pair.of("", false);
    }

    public String decodeToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        return jwt.getSubject();
    }
}
