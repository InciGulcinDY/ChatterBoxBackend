package com.tobeto.ChatterBoxBackend.core.filters;

import com.tobeto.ChatterBoxBackend.core.services.JwtService;
import com.tobeto.ChatterBoxBackend.services.abstracts.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
//  JwtAuthFilter checks every Jwt indicated in each request's header
//  OncePerRequestFilter abstract class is used to check every each request
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtHeader = request.getHeader("Authorization");
        if(jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            // Converting token as not starting with "Bearer "
            String jwt = jwtHeader.substring(7);
            String userName = jwtService.extractUsername(jwt);

            if(userName != null){
                UserDetails user = userService.loadUserByUsername(userName);
                if(jwtService.isTokenValid(jwt, user)) {
                    //  Entry to Spring Security
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    //  Transferring the request to the Spring Security
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);

    }
}
