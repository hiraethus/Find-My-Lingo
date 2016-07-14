package com.clackjones.cymraeg.filter;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HtmlEscapeServletRequestWrapper extends HttpServletRequestWrapper {

    public HtmlEscapeServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);

        String[] escapedHtmlValues = new String[values.length];

        for (int i = 0; i < values.length; ++i) {
            if (values[i] != null) escapedHtmlValues[i] = HtmlUtils.htmlEscape(values[i]);
        }

        return escapedHtmlValues;
    }

    @Override
    public String getParameter(String name) {
        return HtmlUtils.htmlEscape(super.getParameter(name));
    }

    @Override
    public String getHeader(String name) {
        return HtmlUtils.htmlEscape(super.getHeader(name));
    }
}
