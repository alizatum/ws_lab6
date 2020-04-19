package lab6;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersonServiceExceptionMapper implements ExceptionMapper<PersonServiceException> {

    public Response toResponse(PersonServiceException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}