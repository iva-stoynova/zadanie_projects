/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.people;

import javax.xml.ws.WebFault;
 
@WebFault(name="PersonDataException")
public class PersonDataException extends Exception{
    private PersonDataExceptionBean faultBean;
 
    public PersonDataException(String message, PersonDataExceptionBean faultInfo){
        super(message);
        faultBean = faultInfo;
    }
 
    public PersonDataException(String message, PersonDataExceptionBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }
 
    public PersonDataExceptionBean getFaultInfo(){
        return faultBean;
    }
}