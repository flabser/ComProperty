package gisservprovider.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.exponentus.rest.RestProvider;

@Path("gis")
public class GisService extends RestProvider {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		System.out.println("test");
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		System.out.println("test" + id);
		// UNAUTHORIZED
		if (!getSession().getUser().isAuthorized()) {
			return Response.noContent().status(Status.UNAUTHORIZED).build();
		}

		return Response.ok().build();
	}

}
