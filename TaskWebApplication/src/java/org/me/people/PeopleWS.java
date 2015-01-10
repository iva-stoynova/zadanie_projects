/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.people;

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
    @WebMethod(operationName = "createPerson")
    public String createPerson(@WebParam(name = "personData") final PersonData personData) {
        String result;
        result = DBOperations.createPerson(personData);
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findPerson")
    public PersonData findPerson(@WebParam(name = "name") String name) {
        PersonData personData;
        personData = DBOperations.findPerson(name);
        return personData;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updatePerson")
    public String updatePerson(@WebParam(name = "personData") PersonData personData) {
        String result;
        result = DBOperations.updatePerson(personData);
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deletePerson")
    public String deletePerson(@WebParam(name = "id") int id) {
        String result;
        result = DBOperations.deletePerson(id);
        return result;
    }

}
