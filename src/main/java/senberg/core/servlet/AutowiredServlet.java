package senberg.core.servlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AutowiredServlet extends HttpServlet {
    protected HttpServletRequest request;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletConfig.getServletContext());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        super.service(request, response);
    }

    protected Long getRequiredLongParameter(String name) {
        String value = request.getParameter(name);

        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new InvalidServletParameterException(String.format("Invalid required long request parameter value: %s (%s)", name, value));
            }
        } else {
            throw new InvalidServletParameterException(String.format("Missing required long request parameter: %s", name));
        }
    }

    protected Long getOptionalLongParameter(String name) {
        String value = request.getParameter(name);

        if (value != null && value.length() > 0) {
            try {
                return Long.parseLong(value.trim());
            } catch (NumberFormatException e) {
                throw new InvalidServletParameterException(String.format("Invalid optional long request parameter value: %s (%s)", name, value));
            }
        } else {
            return null;
        }
    }

    protected List<Long> getLongParameters(String name) {
        String values[] = request.getParameterValues(name);
        List<Long> result = new ArrayList<>();

        if (values != null) {
            try {
                for (String value : values) {
                    result.add(Long.parseLong(value));
                }
            } catch (NumberFormatException e) {
                throw new InvalidServletParameterException(String.format("Invalid long request parameter values: %s (%s)", name, Arrays.toString(values)));
            }
        }

        return result;
    }

    protected String getRequiredNonEmptyStringParameter(String name) {
        String value = request.getParameter(name);

        if (value != null && value.length() > 0) {
            return value;
        } else {
            throw new InvalidServletParameterException(String.format("Missing required non-empty string request parameter: %s", name));
        }
    }

    protected String getRequiredAllowedEmptyStringParameter(String name) {
        String value = request.getParameter(name);

        if (value != null) {
            return value;
        } else {
            throw new InvalidServletParameterException(String.format("Missing required string request parameter: %s", name));
        }
    }

    protected String getOptionalStringParameter(String name) {
        return request.getParameter(name);
    }

    protected String[] getOptionalStringParameters(String name) {
        return request.getParameterValues(name);
    }
}
