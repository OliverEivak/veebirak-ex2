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

import ee.ttu.olivereivak.veebirakex2.db.StarDAO;
import ee.ttu.olivereivak.veebirakex2.model.Star;
import ee.ttu.olivereivak.veebirakex2.model.form.StarForm;
import ee.ttu.olivereivak.veebirakex2.validator.StarValidator;

@WebServlet(name = "StarServlet", urlPatterns = { "/s" })
public class StarServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(StarServlet.class);

    public void init() {
        log.info("StarServlet starting");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idString = request.getParameter("id");
        Long id = null;
        if (idString != null) {
            try {
                id = Long.valueOf(idString);
                get(request, response, id);
            } catch (NumberFormatException e) {
                showErrorPage(request, response);
                return;
            }
        }

        getAll(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("save".equals(action)) {
            save(request, response);
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StarDAO starDAO = new StarDAO();
        Star[] stars = starDAO.findAll();

        request.setAttribute("stars", stars);

        RequestDispatcher view = request.getRequestDispatcher("stars.jsp");
        view.forward(request, response);
    }

    private void get(HttpServletRequest request, HttpServletResponse response, Long id) throws ServletException, IOException {
        StarDAO starDAO = new StarDAO();
        Star star = starDAO.findById(id);

        if (star != null) {
            request.setAttribute("star", star);

            RequestDispatcher view = request.getRequestDispatcher("star.jsp");
            view.forward(request, response);
        } else {
            showErrorPage(request, response);
        }
    }

    private void showErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Star not found");
        RequestDispatcher view = request.getRequestDispatcher("error.jsp");
        view.forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StarForm starForm = getStarForm(request);
        StarValidator starValidator = new StarValidator();

        Map<String, String> errors = starValidator.validate(starForm);

        if (errors.isEmpty()) {
            Star star = starValidator.parse(starForm);

            StarDAO starDAO = new StarDAO();
            Star updatedStar = starDAO.update(star);

            request.setAttribute("status", "success");
            request.setAttribute("star", updatedStar);

            RequestDispatcher view = request.getRequestDispatcher("star.jsp");
            view.forward(request, response);
        } else {
            request.setAttribute("status", "fail");
            request.setAttribute("errors", errors);
            request.setAttribute("star", starForm);

            RequestDispatcher view = request.getRequestDispatcher("star.jsp");
            view.forward(request, response);
        }
    }

    private StarForm getStarForm(HttpServletRequest request) {
        StarForm starForm = new StarForm();
        starForm.setId(request.getParameter("id"));
        starForm.setCommonName(request.getParameter("commonName"));
        starForm.setDistanceInLightYears(request.getParameter("distanceInLightYears"));
        starForm.setDescription(request.getParameter("description"));
        return starForm;
    }

}
