package org.com.qsqLt.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.com.qsqLt.common.BaseContext;
import org.com.qsqLt.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestUri = httpServletRequest.getRequestURI();
        String[] urls = new String[]{
                "/backend/**",
                "/front/**",
                "/employee/login",
                "/employee/logout",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        if(check(urls,requestUri)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }else{
            if(httpServletRequest.getSession().getAttribute("employee") != null){
                BaseContext.setCurId((Long)httpServletRequest.getSession().getAttribute("employee"));
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return;
            }else if(httpServletRequest.getSession().getAttribute("user") != null){
                BaseContext.setCurId((Long)httpServletRequest.getSession().getAttribute("user"));
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return;
            }else{
                httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
                return;
            }
        }

    }

    public boolean check(String[] urls,String requestURI){
        for(String str : urls){
            boolean match = PATH_MATCHER.match(str, requestURI);
            if(match == true) return true;
        }
        return false;
    }
}
