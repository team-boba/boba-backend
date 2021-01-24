package com.beaconfireboba.backend.security.filter;

import com.beaconfireboba.backend.constant.Constant;
import com.beaconfireboba.backend.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String serializeUserJson = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);

        if(serializeUserJson == null){
            // http://localhost:9999/auth/login
            String authServiceLoginUrl = this.getFilterConfig().getInitParameter("services.auth.login");
            httpServletResponse.sendRedirect(authServiceLoginUrl + "?redirect=" + httpServletRequest.getRequestURL());
        } else{
//            SerializeUser serializeUser = new ObjectMapper().readValue(serializeUserJson, SerializeUser.class);
//            httpServletRequest.setAttribute("user", serializeUser);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
