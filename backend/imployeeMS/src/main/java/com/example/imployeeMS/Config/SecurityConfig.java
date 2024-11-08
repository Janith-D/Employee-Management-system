package com.example.imployeeMS.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable()) // For testing, disable CSRF; enable it in production
                .authorizeRequests(auth -> auth
                .requestMatchers("/api/v1/empcontrol/getAllEmployees","/api/v1/departmentcontroll/getAllDepartment","/api/v1/teamControl/getAllTeam").hasAnyRole("ADMIN", "MANAGER") // Only Admin and Manager
                .requestMatchers("/api/v1/empcontrol/saveemployee", "/api/v1/empcontrol/updateemployee","/api/v1/departmentcontroll/saveDepartment","/api/v1/teamControl/saveTeam").hasRole("ADMIN") // Only Admin
                .requestMatchers("/api/v1/empcontrol/searchemployee/**","/api/v1/departmentcontroll/searchDepartment/**","/api/v1/teamControl/searchTeam/**").hasAnyRole("ADMIN", "EMPLOYEE", "MANAGER") // All roles
                .requestMatchers("/api/v1/empcontrol/deleteemployee/**","/api/v1/departmentcontroll/deleteDepartment/**","/api/v1/teamControl/deleteTeam/**").hasRole("ADMIN") // Only Admin can delete
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                )
                .httpBasic(httpBasicConfigurer -> {}); // Basic Auth for simplicity; for production use OAuth or JWT

        return http.build();

    }
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("manager123")
                .roles("MANAGER")
                .build();

        UserDetails employee = User.withDefaultPasswordEncoder()
                .username("employee")
                .password("employee123")
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(admin,manager,employee);
    }
}
