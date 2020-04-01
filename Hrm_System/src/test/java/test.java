import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


public class test {
    public static void main(String[] args) {
        JwtBuilder claim = Jwts.builder().setId("888").setSubject("小白").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "ihrm")
                .claim("companyName", "传智播客");
        String compact = claim.compact();
        Claims ihrm = Jwts.parser().setSigningKey("ihrm").parseClaimsJws(compact).getBody();
        System.out.println(ihrm.getId());
       System.out.println(ihrm.get("companyName"));
    }
}

