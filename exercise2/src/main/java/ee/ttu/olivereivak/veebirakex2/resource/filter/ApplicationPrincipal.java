package ee.ttu.olivereivak.veebirakex2.resource.filter;

import java.security.Principal;

import ee.ttu.olivereivak.veebirakex2.entity.Authentication;
import ee.ttu.olivereivak.veebirakex2.entity.Role;

public class ApplicationPrincipal implements Principal {

    private Authentication authentication;

    public ApplicationPrincipal (Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public String getName() {
        return String.format("%s:%s", authentication.getToken(), authentication.getUser().getUsername());
    }

    public Role getRole() {
        return authentication.getUser().getRole();
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
