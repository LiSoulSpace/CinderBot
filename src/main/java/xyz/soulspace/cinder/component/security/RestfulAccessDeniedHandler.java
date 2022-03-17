package xyz.soulspace.cinder.component.security;

import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(accessDeniedException.getMessage(), HttpStatus.FORBIDDEN);
        response.getWriter().println(JSONUtil.parse(responseEntity));
        response.getWriter().flush();
    }
}
