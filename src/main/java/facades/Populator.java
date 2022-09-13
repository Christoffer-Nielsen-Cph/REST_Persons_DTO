/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Person;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.util.Date;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        Date date = new Date();
        pf.addPerson(new PersonDTO(new Person("Chris", "Nielsen",25115525,date)));
        pf.addPerson(new PersonDTO(new Person("Frank", "Hvam",23154325,date)));
        pf.addPerson(new PersonDTO(new Person("Henning", "Olsen",21115225,date)));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
