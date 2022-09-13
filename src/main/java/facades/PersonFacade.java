package facades;

import dtos.PersonDTO;
import entities.Person;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import errorhandling.PersonNotFoundException;
import javassist.NotFoundException;
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
    public PersonDTO getPersonById(Long id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person rm = em.find(Person.class, id);
        if (rm == null)
            throw new PersonNotFoundException("The Person entity with ID: "+id+" Was not found");
        return new PersonDTO(rm);
    }

    @Override
    public Person deletePerson(Long id) throws PersonNotFoundException{
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class,id);
        if(person == null) {
            throw new PersonNotFoundException("No such Person with id");
        }
        try{
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO> getAllPersons() throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        if(query == null)
            throw new PersonNotFoundException("No persons were found");
        List<Person> persons = query.getResultList();
        return PersonDTO.getDtos(persons);
    }

    @Override
    public PersonDTO editPerson(PersonDTO personDTO) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Person fromDB = em.find(Person.class,personDTO.getId());
        Date date = new Date();
        if(fromDB == null) {
            throw new PersonNotFoundException("No such Person with id: " + personDTO.getId());
        }
        Person personEntity = new Person(personDTO.getId(),personDTO.getfName(), personDTO.getlName(),personDTO.getPhone(),personDTO.getCreated());

        try {
            em.getTransaction().begin();
            personEntity.setLastEdited(date);
            em.merge(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = getPersonFacade(emf);

    }
}
