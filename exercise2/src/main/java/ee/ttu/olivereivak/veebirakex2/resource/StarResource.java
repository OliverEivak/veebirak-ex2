package ee.ttu.olivereivak.veebirakex2.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.inject.persist.Transactional;

import ee.ttu.olivereivak.veebirakex2.entity.Star;
import ee.ttu.olivereivak.veebirakex2.services.StarService;

public class StarResource extends BaseResource implements IStarResource {

    @Inject
    private StarService starService;

    @GET
    @Path("{starID}")
    @Transactional
    public Star get(@PathParam("starID") Long id) {
        return starService.getByID(id);
    }

    @GET
    @Transactional
    public List<Star> getAll() {
        return starService.getAll();
    }

    @POST
    @Transactional
    public Star update(Star star) {
        return starService.update(star);
    }
}
