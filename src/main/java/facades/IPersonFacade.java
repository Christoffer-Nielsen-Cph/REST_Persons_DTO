package facades;

import dtos.PersonDTO;
import entities.Person;
import errorhandling.PersonNotFoundException;
import javassist.NotFoundException;

import java.util.List;

public interface IPersonFacade {

    public PersonDTO addPerson(PersonDTO personDTO);
    public Person deletePerson(Long id) throws PersonNotFoundException;
    public PersonDTO getPersonById(Long id) throws PersonNotFoundException;
    public List<PersonDTO> getAllPersons() throws PersonNotFoundException;
    public PersonDTO editPerson(PersonDTO personDTO) throws PersonNotFoundException;

}
