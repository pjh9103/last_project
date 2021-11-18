package kr.co.tjoeun.security;

import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class MemberLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        String errorMessage = "";
        if (exception instanceof AuthenticationServiceException) {
            errorMessage = "존재하지 않는 사용자입니다.";

        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호가 틀립니다.";

        } else if (exception instanceof LockedException) {
            errorMessage = "잠긴 계정입니다..";

        } else if (exception instanceof DisabledException) {
            errorMessage = "비활성화된 계정입니다..";

        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "만료된 계정입니다..";

        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호가 만료되었습니다.";
        }

        if (exception.getMessage() != null) {
            setDefaultFailureUrl("/login?error=true&exceptionMessage=" + URLEncoder.encode(exception.getMessage(),"UTF-8"));
        } else {
            setDefaultFailureUrl("/login?error=true&exceptionMessage=" + URLEncoder.encode(errorMessage,"UTF-8"));
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
