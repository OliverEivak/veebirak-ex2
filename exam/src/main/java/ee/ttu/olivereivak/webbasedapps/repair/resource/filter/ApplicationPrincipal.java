package ee.ttu.olivereivak.webbasedapps.repair.resource.filter;

import java.security.Principal;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.SubjectType;

public class ApplicationPrincipal implements Principal {

    private Authentication authentication;

    public ApplicationPrincipal (Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public String getName() {
        return String.format("%s:%s", authentication.getToken(), authentication.getUserAccount().getUsername());
    }

    public SubjectType getSubjectType() {
        return authentication.getUserAccount().getSubjectType();
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
