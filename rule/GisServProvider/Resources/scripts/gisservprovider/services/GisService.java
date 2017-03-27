package gisservprovider.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.rest.RestProvider;

import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;

@Path("gis")
public class GisService extends RestProvider {

	@GET
	@Path("/getbycoord/{coord}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("coord") String coord) {
		System.out.println("coordinates = " + coord);
		try {
			RealEstateDAO reDao = new RealEstateDAO(getSession());
			ViewPage<RealEstate> viewPage = reDao.findByCoord(coord, 0, 0);

			return Response.ok(viewPage).build();
		} catch (DAOException e) {
			return responseException(e);
		}
	}

	@GET
	@Path("/getbystreet/{street_id}/{building_num}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("street_id") String streetId, @PathParam("building_num") String buildingNum) {
		try {
			RealEstateDAO reDao = new RealEstateDAO(getSession());
			ViewPage<RealEstate> viewPage = reDao.findByStreetAndHome(streetId, buildingNum, 0, 0);
			return Response.ok(viewPage).build();
		} catch (DAOException e) {
			return responseException(e);
		}
	}

}
