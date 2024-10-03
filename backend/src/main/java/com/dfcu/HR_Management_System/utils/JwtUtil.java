/**
 * Created By Eng. Pius Obonyo
 * Date: 6/21/24
 * Time: 6:06 PM
 */

package com.dfcu.HR_Management_System.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String secret = "e83f3851fa7407cb60737b93251ce8ca0c85ccfef4bb304792f7fe2a4cb7cb39e83f3851fa7407cb60737b93251ce8ca0c85ccfef4bb304792f7fe2a4cb7cb39";

    // pass the value from createToken and generate token
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    //create token
    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); // compact() does generate token
    }

    private Key getSignKey(){
        byte[] keybytes  = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keybytes);
    }
    // Extract  username
    public String extractUsername (String token){
        return  extractClaims(token, Claims::getSubject);
    }

    // writing methods to handle extracting claims
    public <T> T extractClaims(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }


    // check if token is expired or not
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration time
    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);

    }

    // To validate token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
