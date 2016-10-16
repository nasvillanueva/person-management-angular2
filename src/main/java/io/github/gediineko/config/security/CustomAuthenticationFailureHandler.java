package io.github.gediineko.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ggolong on 10/16/16.
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setHeader("Cache-Control", "no-cache");

        String message = "Authentication Failed";

        if(e instanceof LockedException){
            message = "User is Locked";
        } else if(e instanceof UsernameNotFoundException){
            message = "Username not found";
        } else if(e instanceof BadCredentialsException){
            message = "Invalid username/password";
        }

        objectMapper.writeValue(httpServletResponse.getWriter(), message);
    }
}
