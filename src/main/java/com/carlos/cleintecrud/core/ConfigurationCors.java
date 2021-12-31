package com.carlos.cleintecrud.core;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ConfigurationCors {

	
	@Bean
	public CorsFilter corsFilter() {

	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    config.setMaxAge(3600L);

	    config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", config);

	    return new CorsFilter(source);
	}
}
