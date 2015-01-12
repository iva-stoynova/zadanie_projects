/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.people;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Iva Stoynova
 */
@WebService(serviceName = "PeopleWS")
@Stateless()
public class PeopleWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findPersons")
    public List findPersons(@WebParam(name = "name") String name) throws PersonDataException {
        List<PersonData> list;
        try {
            list = DBOperations.findPersons(name);
        } catch (Exception ex) {
            throw new PersonDataException(ex.getMessage(), new PersonDataExceptionBean());
        }
        return list;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "createPerson")
    public String createPerson(@WebParam(name = "personData") PersonData personData) throws PersonDataException {
        String message;
        try {
            message = DBOperations.createPerson(personData);
        } catch (Exception ex) {
            throw new PersonDataException(ex.getMessage(), new PersonDataExceptionBean());
        }
        return message;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deletePerson")
    public void deletePerson(@WebParam(name = "id") int id) throws PersonDataException {
        try {
            DBOperations.deletePerson(id);
        } catch (Exception ex) {
            throw new PersonDataException(ex.getMessage(), new PersonDataExceptionBean());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updatePerson")
    public String updatePerson(@WebParam(name = "personData") PersonData personData) throws PersonDataException {
        String message;
        try {
            message = DBOperations.updatePerson(personData);
        } catch (Exception ex) {
            throw new PersonDataException(ex.getMessage(), new PersonDataExceptionBean());
        }
        return message;
    }


}
