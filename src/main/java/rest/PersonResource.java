package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import errorhandling.PersonNotFoundException;
//import jdk.javadoc.internal.doclets.toolkit.Content;
import javassist.NotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }


    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws PersonNotFoundException {
        List<PersonDTO> persons = new ArrayList<>();

        for (PersonDTO person : FACADE.getAllPersons()) {
            persons.add(new PersonDTO(person));
        }
        for (RenameMe renameMe : FACADE.getAll()) {
            dtoList.add(new RenameMeDTO(renameMe));
        }
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(dtoList))
                .build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(@PathParam("id") long id, String jsonInput) throws PersonNotFoundException {
        PersonDTO personDTO = GSON.fromJson(jsonInput   ,PersonDTO.class);
        personDTO.setId(id);
        PersonDTO returned = FACADE.editPerson(personDTO);
        return Response.ok().entity(GSON.toJson(returned)).build();

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) throws PersonNotFoundException {
        PersonDTO deleted = new PersonDTO(FACADE.deletePerson(id));
        return Response
                .ok("SUCCESS")
                .entity(GSON.toJson(deleted))
                .build();
    }





}
