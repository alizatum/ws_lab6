package lab6;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
                                   @QueryParam("surname") String surname,
                                   @QueryParam("age") Integer age,
                                   @QueryParam("phone") Integer phone,
                                   @QueryParam("gender") String gender) throws PersonServiceException {
        validateParams(name, surname, age, phone, gender);
        return new PostgreSQLDAO().getPersons(name, surname, age, phone, gender);
    }

    @POST
    public Integer addPerson(Person person) throws PersonServiceException {
        validateParams(person.getName(), person.getSurname(), person.getAge(), person.getPhone(), person.getGender());
        return new PostgreSQLDAO().addPerson(person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
    }

    @PUT
    public String updatePerson(Integer id, Person person) throws PersonServiceException {
        if (id == null || id <= 0)
            throw new PersonServiceException("Invalid id!");
        validateParams(person.getName(), person.getSurname(), person.getAge(), person.getPhone(), person.getGender());
        return new PostgreSQLDAO().updatePerson(id, person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
    }

    @DELETE
    public String deletePerson(Integer id) throws PersonServiceException {
        if (id == null || id <= 0)
            throw new PersonServiceException("Invalid id!");
        return new PostgreSQLDAO().deletePerson(id);
    }


    private static void validateParams(String name, String surname, Integer age,
                                      Integer phone, String gender) throws PersonServiceException {
        if (name == null || name.isEmpty())
            throw new PersonServiceException("Invalid name!");
        if (surname == null || surname.isEmpty())
            throw new PersonServiceException("Invalid surname!");
        if (age == null || age <= 0)
            throw new PersonServiceException("Invalid age!");
        if (phone == null || phone <= 100000)
            throw new PersonServiceException("Invalid phone!");
        if (gender == null || gender.isEmpty())
            throw new PersonServiceException("Invalid gender!");
    }
}