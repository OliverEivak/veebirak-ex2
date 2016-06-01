package ee.ttu.olivereivak.webbasedapps.repair.resource.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.services.ServiceRequestService;

/**
 * This whole class only exists because the exam requires a server side
 * template language to be used for at least one page.
 *
 * So this servlet provides an alternative service requests view -
 * the same view is also available in the Angular app.
 */

@Slf4j
@Singleton
public class ServiceRequestServlet extends HttpServlet {

    @Inject
    private Configuration configuration;

    @Inject
    private ServiceRequestService serviceRequestService;

    @Inject
    private AuthenticationDAO authenticationDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req, resp)) {
            // Create a data-model
            Map root = new HashMap();
            root.put("serviceRequests", serviceRequestService.getAll());

            // Get the template (uses cache internally)
            Template temp = configuration.getTemplate("serviceRequest.ftl");

            // Merge data-model with template
            try {
                temp.process(root, resp.getWriter());
            } catch (TemplateException e) {
                log.error("Failed to merge model with template", e);
            }
        } else {
            sendError(resp);
        }
    }

    private boolean isAuthenticated(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String token = req.getParameter("token");

        Authentication authentication = authenticationDAO.findByToken(token);
        return (authentication != null && username.equals(authentication.getUserAccount().getUsername()));
    }

    private void sendError(HttpServletResponse resp) {
        try {
            resp.sendError(401, "Unauthorized - please provide a username and token query parameter");
        } catch (IOException e) {
            log.error("Error sending error message", e);
        }
    }

}
