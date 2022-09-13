package facades;

import dtos.PersonDTO;
import errorhandling.PersonNotFoundException;

import java.util.Date;

public interface IPersonFacade {

    public PersonDTO addPerson(PersonDTO personDTO);
    public PersonDTO deletePerson(long id) throws PersonNotFoundException;
    public PersonDTO getPersonById(long id) throws PersonNotFoundException;
    public PersonDTO getAllPersons() throws PersonNotFoundException;
    public PersonDTO editPerson(PersonDTO personDTO) throws PersonNotFoundException;

}
