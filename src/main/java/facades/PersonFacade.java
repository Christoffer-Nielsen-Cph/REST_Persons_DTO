package facades;

import dtos.PersonDTO;
import entities.Person;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        Date date = new Date();
        Person personEntity = new Person(personDTO.getfName(), personDTO.getlName(),personDTO.getPhone(),date);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }
    @Override
    public PersonDTO getPersonById(long id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person rm = em.find(Person.class, id);
        if (rm == null)
            throw new PersonNotFoundException("The Person entity with ID: "+id+" Was not found");
        return new PersonDTO(rm);
    }

    @Override
    public PersonDTO deletePerson(long id) {
        return null;
    }

    @Override
    public PersonDTO getAllPersons() {
        return null;
    }

    @Override
    public PersonDTO editPerson(PersonDTO personDTO) {
        return null;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = getPersonFacade(emf);

    }
}
