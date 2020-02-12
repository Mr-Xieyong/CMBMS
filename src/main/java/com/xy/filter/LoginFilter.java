package com.xy.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    public LoginFilter() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        Integer userid = (Integer) session.getAttribute("user_id");
        if (userid != null) {
            // 用户已经登录
			((HttpServletResponse)response).sendRedirect("/WEB-INF/admin/login.jsp");
            chain.doFilter(request, response);
        } else {
            // 没有登录，就重定向到登录界面
            System.out.println("重定向..........");
            ((HttpServletResponse) response).sendRedirect("/WEB-INF/admin/index.jsp");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
