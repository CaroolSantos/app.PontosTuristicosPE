package resources;

import dao.UserDao;
import exceptions.BadRequestException;
import model.User;
import model.forms.LoginForm;
import model.forms.RegisterForm;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/api/users")
public class UserResource {

    final static Logger logger = Logger.getLogger(UserResource.class.getName());

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer id) {

        UserDao userDao = new UserDao();

        User user = userDao.get(id);

        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(LoginForm form) {

        UserDao userDao = new UserDao();

        User user = userDao.getByEmail(form.email);

        if (user == null || !user.getPassword().equals(form.password)) {
            throw new BadRequestException("Usuário ou senha inválidos");
        }

        return Response.ok(user).build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(RegisterForm form) {

        UserDao userDao = new UserDao();

        User user = new User(form.name, form.email, form.password);
        user = userDao.save(user);

        return Response.ok(user).build();
    }

}
