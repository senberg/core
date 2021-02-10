package senberg.core.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class JSONServlet extends AutowiredServlet {

    protected final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            String data = process(request);

            if (data != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                gson.toJson(data, response.getWriter());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        catch(InvalidServletParameterException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            gson.toJson(e, response.getWriter());
        }
        catch(Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            gson.toJson(e, response.getWriter());
        }
    }

    abstract protected String process(HttpServletRequest request);
}