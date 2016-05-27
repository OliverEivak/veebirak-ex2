package ee.ttu.olivereivak.webbasedapps.repair.resource.filter;

import static ee.ttu.olivereivak.webbasedapps.repair.guice.GuiceInjector.getInjector;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;

@Slf4j
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        String token = headers.getFirst("Auth-Token");
        String username = headers.getFirst("Username");

        if (token != null && username != null) {
            Authentication authentication = getInjector().getInstance(AuthenticationDAO.class).findByToken(token);

            if (authentication != null && username.equals(authentication.getUserAccount().getUsername())) {
                ApplicationPrincipal applicationPrincipal = new ApplicationPrincipal(authentication);
                ApplicationSecurityContext applicationSecurityContext = new ApplicationSecurityContext(applicationPrincipal);
                requestContext.setSecurityContext(applicationSecurityContext);
            } else {
                log.info("Authentication NOT valid for " + username + " with token " + token);
                requestContext.abortWith(Response.status(UNAUTHORIZED).build());
            }
        }
    }
}
