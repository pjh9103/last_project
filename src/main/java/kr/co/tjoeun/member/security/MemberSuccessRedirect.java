package kr.co.tjoeun.member.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static kr.co.tjoeun.member.security.SecurityConstants.*;

public class MemberSuccessRedirect implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            HttpServletRequest httpReqeust = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            List<String> urls = Arrays.asList(PATH_JOIN, PATH_LOGIN);
            for (String url : urls) {
                if (httpReqeust.getRequestURI().contains(url)) {
                    httpResponse.sendRedirect(PATH_MAIN);
                }
            }
        }
        chain.doFilter(request,response);
    }
}
