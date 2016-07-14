package com.clackjones.cymraeg.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EscapeHtmlInputFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        chain.doFilter(new HtmlEscapeServletRequestWrapper((HttpServletRequest) request), response);
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}
