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
 * @author vesko
 */
@WebService(serviceName = "PeopleWS")
@Stateless()
public class PeopleWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "add")
    public int add(@WebParam(name = "i") int i, @WebParam(name = "j") int j) {
        int k = i + j;
        return k;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createPerson")
    public String createPerson(@WebParam(name = "personData") final PersonData personData) {
        String result;
        result = DBOperations.createPerson(personData);
        return result;
    }

}
