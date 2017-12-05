package resources;

import dao.PlaceDao;
import dao.UserDao;
import exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Category;
import model.Place;
import model.User;
import model.forms.LoginForm;
import model.forms.RegisterForm;

@Path("/api/places")
public class PlaceResource {

  final static Logger logger = Logger.getLogger(PlaceResource.class.getName());

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("id") Integer id) {

    PlaceDao placeDao = new PlaceDao();

    Place place = placeDao.get(id);

    return Response.ok(place).build();
  }

  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() {

    PlaceDao placeDao = new PlaceDao();

    List<Place> places = placeDao.listAll();

    GenericEntity<List<Place>> entity = new GenericEntity<List<Place>>(places) {
    };
    return Response.ok(entity).build();
  }

  @GET
  @Path("/category/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("id") int categoryId) {

    PlaceDao placeDao = new PlaceDao();

    List<Place> places = placeDao.listByCategory(categoryId);

    GenericEntity<List<Place>> entity = new GenericEntity<List<Place>>(places) {
    };
    return Response.ok(entity).build();
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@QueryParam("category") int categoryId, @QueryParam("city") int cityId) {

    PlaceDao placeDao = new PlaceDao();

    List<Place> places = placeDao.listByCategoryAndCity(categoryId, cityId);

    GenericEntity<List<Place>> entity = new GenericEntity<List<Place>>(places) {
    };

    return Response.ok(entity).build();
  }

  @GET
  @Path("/search")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@QueryParam("q") String query) {

    PlaceDao placeDao = new PlaceDao();

    List<Place> places = placeDao.searchPlaceByName(query);

    GenericEntity<List<Place>> entity = new GenericEntity<List<Place>>(places) {
    };

    return Response.ok(entity).build();
  }

}
