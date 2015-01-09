package org.me.people;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

public class DBOperations {
    private static final String dbURL = "jdbc:derby://localhost:1527/zadanie;user=root;password=root";
    private static final String tableName = "root.T_PEOPLE";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static boolean isConnected() {
        return conn != null;
    }
    
    public static String createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        return null;
    }
    
    public static boolean shutdown() {
        try {
            if(stmt != null) {
               stmt.close();
            }
            if(conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        }
        catch(SQLException e) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }
    
    public static PersonData findPerson(String name) {
        if(!isConnected()) {
            if(createConnection() != null) {
                return null;
            }
        }
        PersonData personData;
        try {
            personData = new PersonData();
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName + "where full_name='" + name + "'");
            while(results.next()) {
                personData.setID(results.getInt(1));
                personData.setFULL_NAME(results.getString(2));
                personData.setPIN(results.getString(3));
                personData.setEMAIL(results.getString(4));       
            }
        } catch (SQLException e) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

        return personData;
    }
    
    public static String createPerson(PersonData personData) {
        String validateMessage;
        validateMessage = validatePersonData(personData);
        if(validateMessage != null) {
            return validateMessage;
        }
        String result = createPersonInternal(personData);
        return result;
    }
    
    private static String createPersonInternal(PersonData personData) {
        if(!isConnected()) {
            String resultString = createConnection();
            if(resultString != null) {
                return resultString;
            }
        }
        
        try {
            stmt = conn.createStatement();
            // insert into root.T_PEOPLE(FULL_NAME, PIN, EMAIL) VALUES ('ИВАН ПЕТРОВ ИВАНОВ', NULL, NULL);
            String queryString = "insert into " + tableName + "(FULL_NAME, PIN, EMAIL) VALUES('" + personData.getFULL_NAME() + "',";
            if(personData.getPIN().isEmpty()) {
                queryString += "NULL";
            }
            else {
                queryString += "'" + personData.getPIN() + "'";
            }
            queryString += ",";
            if(personData.getEMAIL().isEmpty()) {
                queryString += "NULL";
            }
            else {
                queryString += "'" + personData.getEMAIL() + "'";
            }
            queryString += ")";
            stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, e);
            return e.getMessage();
        }
        return null;
    }
    
    public static String updatePerson(PersonData personData) {
        String validateMessage;
        validateMessage = validatePersonData(personData);
        if(validateMessage != null) {
            return validateMessage;
        }
        String result = updatePersonInternal(personData);
        return result;
    }
    
    private static String updatePersonInternal(PersonData personData) {
        if(!isConnected()) {
            String resultString = createConnection();
            if(resultString != null) {
                return resultString;
            }
        }
        
        try {
            stmt = conn.createStatement();
            // insert into root.T_PEOPLE(FULL_NAME, PIN, EMAIL) VALUES ('ИВАН ПЕТРОВ ИВАНОВ', NULL, NULL);
            String queryString = "update " + tableName + "set FULL_NAME=" + "'" +
                    personData.getFULL_NAME() + "',PIN=";
            if(personData.getPIN().isEmpty()) {
                queryString += "NULL";
            }
            else {
                queryString += "'" + personData.getPIN() + "'";
            }
            queryString += ",EMAIL=";
            if(personData.getEMAIL().isEmpty()) {
                queryString += "NULL";
            }
            else {
                queryString += "'" + personData.getEMAIL() + "'";
            }
            queryString += " where ID=" + personData.getID();
            stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, e);
            return e.getMessage();
        }
        return null;
    }
    
    public static String deletePerson(int id) {
         if(!isConnected()) {
            String resultString = createConnection();
            if(resultString != null) {
                return resultString;
            }
        }
         
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from " + tableName + " where ID=" + id);
        } catch (SQLException e) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, e);
            return e.getMessage();
        }
        return null;
    }
    
    private static String validatePersonData(PersonData personData) {
        String full_name = personData.getFULL_NAME();
        if(full_name.isEmpty()) {
            return "Name field cannot be empty";
        }
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9а-яА-Я\\ \\-]");
        boolean hasOtherCharacter = pattern.matcher(full_name).find();
        if(hasOtherCharacter) {
            return "Name must contain only latin or cyrillic letters, a space or a dash";
        }
        String pin = personData.getPIN();
        if(!pin.isEmpty()) {
            pattern = Pattern.compile("^[0-9]");
            hasOtherCharacter = pattern.matcher(pin).find();
            if(hasOtherCharacter) {
                return "PIN must contain numbers only";
            }            
            if(pin.length() != 10) {
                return "PIN must be exactly 10 digits";
            }
        }
        String email = personData.getEMAIL();
        if(!email.isEmpty()) {
            EmailValidator emailValidator = EmailValidator.getInstance();
            boolean isAddressValid = emailValidator.isValid(email);
            if(!isAddressValid) {
                return "Email address is not valid";
            }
        }
        return null;
    }
}