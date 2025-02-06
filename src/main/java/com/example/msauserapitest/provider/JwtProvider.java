package com.example.msauserapitest.provider;

import com.example.msauserapitest.auth.provider.dto.AccessToken;
import com.example.msauserapitest.auth.provider.dto.AccessTokenContent;
import com.example.msauserapitest.auth.provider.dto.AccessTokenValidationResult;
import com.example.msauserapitest.auth.utils.DateUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider implements AccessTokenProvider, InitializingBean {
    // 일단 되는지 테스트하고 project level 에서 바깥으로 빼는게 좋다.
    private final String SECRET_KEY = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK";

    private Key key;

    @Override
    public AccessToken create(Long userId) {
        return this.create(userId, false);
    }

    @Override
    public AccessToken create(Long userId, Boolean isManager) {
        LocalDateTime issuedDate = LocalDateTime.now();
        LocalDateTime expiredDate = issuedDate.plusMinutes(620620);

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("isManager", isManager);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(DateUtils.toDate(issuedDate))
                .setExpiration(DateUtils.toDate(expiredDate))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new AccessToken(accessToken);
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public AccessTokenValidationResult validate(AccessToken accessToken) {
        try {
            Jws<Claims> claimsJws = parseClaimsJws(accessToken);
            validateExpiration(claimsJws.getBody());

            return AccessTokenValidationResult.success();
        } catch (SignatureException e) {
            return AccessTokenValidationResult.fail("SIGNATURE_ERROR");
        } catch (MalformedJwtException e) {
            return AccessTokenValidationResult.fail("MALFORMED_ERROR");
        } catch (ExpiredJwtException e) {
            return AccessTokenValidationResult.fail("EXPIRED_ERROR");
        } catch (UnsupportedJwtException e) {
            return AccessTokenValidationResult.fail("UNSUPPORTED_ERROR");
        } catch (IllegalArgumentException e) {
            return AccessTokenValidationResult.fail("JWT 토큰이 잘못되었습니다.");
        }
    }

    @Override
    public AccessTokenContent getContent(AccessToken token) {
        Jws<Claims> claimsJws = parseClaimsJws(token);

        String userId = claimsJws.getBody().getSubject();

        return new AccessTokenContent(Long.valueOf(userId));
    }

    private void validateExpiration(Claims body) {
        Date expiration = body.get("exp", Date.class);

        if (expiration.before(new Date())) {
            throw new ExpiredJwtException(null, null, null);
        }
    }

    private Jws<Claims> parseClaimsJws(AccessToken token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .setAllowedClockSkewSeconds(3000L)
                .build()
                .parseClaimsJws(token.getValue());
    }

    @Override
    public Optional<Object> getClaimValue(AccessToken accessToken, String claimName) {
        return Optional.ofNullable(parseClaimsJws(accessToken).getBody().get(claimName));
    }
}
