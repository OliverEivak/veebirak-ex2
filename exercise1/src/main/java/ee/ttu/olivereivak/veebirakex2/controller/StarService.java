package ee.ttu.olivereivak.veebirakex2.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import ee.ttu.olivereivak.veebirakex2.db.StarDAO;
import ee.ttu.olivereivak.veebirakex2.model.Star;
import ee.ttu.olivereivak.veebirakex2.model.form.StarForm;
import ee.ttu.olivereivak.veebirakex2.validator.StarValidator;

@WebServlet(name = "StarService", urlPatterns = { "/starservice" })
public class StarService extends HttpServlet {

    private static final Logger log = LogManager.getLogger(StarService.class);

    public void init() {
        log.info("StarService starting");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idString = request.getParameter("id");
        Long id = null;
        if (idString != null) {
            try {
                id = Long.valueOf(idString);
                get(response, id);
            } catch (NumberFormatException ignored) {
                writeEmptyObject(response);
            }
        } else {
            writeEmptyObject(response);
        }
    }

    private void get(HttpServletResponse response, Long id) throws ServletException, IOException {
        StarDAO starDAO = new StarDAO();
        Star star = starDAO.findById(id);

        if (star != null) {
            Gson gson = new Gson();
            String json = gson.toJson(star);

            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            writeEmptyObject(response);
        }
    }

    private void writeEmptyObject(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().write("{}");
    }
}