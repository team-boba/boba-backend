package com.beaconfireboba.backend.security.util;

import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;

public class JwtUtil {

    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) return null;

        try {
            String jwtSubject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
            return jwtSubject;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
        }
        return null;
    }
}
