package gisservprovider.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.exponentus.rest.RestProvider;

import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;

@Path("gis")
public class GisService extends RestProvider {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		System.out.println("test");
		return Response.ok().build();
	}

	@GET
	@Path("/{coord}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("coord") String coord) {
		System.out.println("test" + coord);
		RealEstateDAO reDao = new RealEstateDAO(getSession());
		RealEstate entity = reDao.findByCoord(coord);

		return Response.ok(entity).build();
	}

}
