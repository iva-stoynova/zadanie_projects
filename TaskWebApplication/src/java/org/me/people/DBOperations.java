package org.me.people;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
    
    public static void createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        conn = DriverManager.getConnection(dbURL);
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
    
    public static LinkedList<PersonData> findPersons(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if(!isConnected()) {
            createConnection();
        }
        
        LinkedList<PersonData> personDataList = new LinkedList<>();        
        PersonData personData;
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName + " where upper(full_name)=upper('" + name + "')");
            while(results.next()) {
                personData = new PersonData();
                personData.setID(results.getInt(1));
                personData.setFULL_NAME(results.getString(2));
                personData.setPIN(results.getString(3));
                personData.setEMAIL(results.getString(4));
                personDataList.add(personData);
            }
        } catch (SQLException e) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return personDataList;
    }
    
    public static String createPerson(PersonData personData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String validateMessage;
        validateMessage = validatePersonData(personData);
        if(validateMessage != null) {
            return validateMessage;
        }
        createPersonInternal(personData);
        return null;
    }
    
    private static void createPersonInternal(PersonData personData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if(!isConnected()) {
            createConnection();
        }       

        stmt = conn.createStatement();
        // insert into root.T_PEOPLE(FULL_NAME, PIN, EMAIL) VALUES ('ИВАН ПЕТРОВ ИВАНОВ', NULL, NULL);
        String queryString = "insert into " + tableName + "(FULL_NAME, PIN, EMAIL) VALUES('" + personData.getFULL_NAME() + "',";
        if(personData.getPIN() == null || personData.getPIN().isEmpty()) {
            queryString += "NULL";
        }
        else {
            queryString += "'" + personData.getPIN() + "'";
        }
        queryString += ",";
        if(personData.getEMAIL() == null || personData.getEMAIL().isEmpty()) {
            queryString += "NULL";
        }
        else {
            queryString += "'" + personData.getEMAIL() + "'";
        }
        queryString += ")";
        stmt.executeUpdate(queryString);
    }
    
    public static String updatePerson(PersonData personData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String validateMessage;
        validateMessage = validatePersonData(personData);
        if(validateMessage != null) {
            return validateMessage;
        }
        String result = updatePersonInternal(personData);
        return result;
    }
    
    private static String updatePersonInternal(PersonData personData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if(!isConnected()) {
            createConnection();
        }        

        stmt = conn.createStatement();
        String queryString = "update " + tableName + "set FULL_NAME=" + "'" +
                personData.getFULL_NAME() + "',PIN=";
        if(personData.getPIN() == null || personData.getPIN().isEmpty()) {
            queryString += "NULL";
        }
        else {
            queryString += "'" + personData.getPIN() + "'";
        }
        queryString += ",EMAIL=";
        if(personData.getEMAIL() == null || personData.getEMAIL().isEmpty()) {
            queryString += "NULL";
        }
        else {
            queryString += "'" + personData.getEMAIL() + "'";
        }
        queryString += " where ID=" + personData.getID();
        stmt.executeUpdate(queryString);

        return null;
    }
    
    public static void deletePerson(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
         if(!isConnected()) {
            createConnection();
        }
         
        stmt = conn.createStatement();
        stmt.executeUpdate("delete from " + tableName + " where ID=" + id);
    }
    
    private static String validatePersonData(PersonData personData) {
        String full_name = personData.getFULL_NAME();
        if(full_name.isEmpty()) {
            return "Name field cannot be empty";
        }
        if(full_name.length() > 90) {
            return "Name field cannot be longer than 90 characters";
        }
        Pattern pattern = Pattern.compile("[^a-zA-Zа-яА-Я\\ \\-]");
        boolean hasOtherCharacter = pattern.matcher(full_name).find();
        if(hasOtherCharacter) {
            return "Name must contain only latin or cyrillic letters, a space or a dash";
        }
        String pin = personData.getPIN();
        if(pin != null && !pin.isEmpty()) {
            if(pin.length() != 10) {
                return "PIN must be exactly 10 digits";
            }
            pattern = Pattern.compile("[^0-9]");
            hasOtherCharacter = pattern.matcher(pin).find();
            if(hasOtherCharacter) {
                return "PIN must contain numbers only";
            }            
        }
        String email = personData.getEMAIL();
        if(email != null && !email.isEmpty()) {
            if(email.length() > 40) {
                return "Email cannot be longer than 40 characters";
            }
            EmailValidator emailValidator = EmailValidator.getInstance();
            boolean isAddressValid = emailValidator.isValid(email);
            if(!isAddressValid) {
                return "Email address is not valid";
            }
        }
        return null;
    }
}