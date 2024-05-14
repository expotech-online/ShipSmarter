package org.ahmedukamel.shipsmarter.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.shipsmarter.constant.JwtConstants;
import org.ahmedukamel.shipsmarter.model.AccessToken;
import org.ahmedukamel.shipsmarter.model.User;
import org.ahmedukamel.shipsmarter.repository.AccessTokenRepository;
import org.ahmedukamel.shipsmarter.service.db.DatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JsonWebTokenService implements IAccessTokenService {
    final AccessTokenRepository repository;

    @Value(value = "${application.security.secret-key}")
    private String secretKey;
    //
    @Value(value = "${application.security.expiration}")
    private long expiration;

    @Override
    public String generate(User user) {
        String token = generate(user.getUsername(), Map.ofEntries(
                Map.entry(JwtConstants.CLAIM_PROVIDER, user.getProvider()),
                Map.entry(JwtConstants.CLAIM_AUTHORITIES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()),
                Map.entry(JwtConstants.CLAIM_ROLE, user.getRole())
        ));

        AccessToken accessToken = new AccessToken();
        accessToken.setToken(token);
        accessToken.setUser(user);
        accessToken.setCreation(getClaim(token, Claims::getIssuedAt));
        accessToken.setExpiration(getClaim(token, Claims::getExpiration));

        repository.save(accessToken);

        return token;
    }

    @Override
    public User getUser(String token) {
        return DatabaseService.get(repository::findById, token, AccessToken.class).getUser();
    }

    @Override
    public void revoke(String token) {
        AccessToken accessToken = DatabaseService.get(repository::findById, token, AccessToken.class);
        accessToken.setRevoked(true);
        repository.save(accessToken);
    }

    @Override
    public void revokeAll(User user) {
        repository.revokeAll(user);
    }

    private String generate(String username, Map<String, ?> claims) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(JwtConstants.ISSUER)
                .setSubject(username)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        return function.apply(getClaims(token));
    }
}