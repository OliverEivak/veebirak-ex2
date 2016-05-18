package ee.ttu.olivereivak.veebirakex2.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.veebirakex2.entity.Star;

@Path("/stars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IStarResource {

    @GET
    @Path("{starID}")
    Star get(@PathParam("starID") Long id);

    @GET
    List<Star> getAll();

    @POST
    Star update(Star star);

}
