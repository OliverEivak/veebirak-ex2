package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ILoginResource {

    @POST
    Authentication login(LoginForm loginForm);

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class LoginForm {
        private String username;
        private String password;
    }

}
