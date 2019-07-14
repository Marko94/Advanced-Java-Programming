import models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class Rest
{
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkUser()
    {
        return "Hello";
    }

    @POST
    @Path("/user")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public User checkUser(String str)
    {
        return new User(str, "pass");
    }
}

