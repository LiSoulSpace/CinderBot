package xyz.soulspace.cinder.component.security;

import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(authException.getMessage(), HttpStatus.UNAUTHORIZED);
        response.getWriter().println(JSONUtil.parse(responseEntity));
        response.getWriter().flush();
    }
}
