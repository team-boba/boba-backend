package com.beaconfireboba.backend.config;

import com.beaconfireboba.backend.security.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {

    @Value("${services.auth.login}")
    private String authServiceLoginUrl;

    @Value("${services.auth.logout}")
    private String authServiceLogoutUrl;

    @Value("${services.auth.register}")
    private String authServiceRegisterUrl;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth.login", authServiceLoginUrl));

        return registrationBean;
    }

    public String getAuthServiceLoginUrl() {
        return authServiceLoginUrl;
    }

    public String getAuthServiceLogoutUrl() {
        return authServiceLogoutUrl;
    }

    public String getAuthServiceRegisterUrl() {
        return authServiceRegisterUrl;
    }

}
