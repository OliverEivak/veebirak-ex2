package ee.ttu.olivereivak.veebirakex2.resource.filter;

import static ee.ttu.olivereivak.veebirakex2.guice.GuiceInjector.getInjector;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import ee.ttu.olivereivak.veebirakex2.dao.AuthenticationDAO;
import ee.ttu.olivereivak.veebirakex2.entity.Authentication;

import lombok.extern.slf4j.Slf4j;

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

            if (authentication != null && username.equals(authentication.getUser().getUsername())) {
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
