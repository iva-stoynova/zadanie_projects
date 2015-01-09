package org.me.people;

public class PersonData {
    private String FULL_NAME;
    private String PIN;
    private String EMAIL;

    /**
     * @return the FULL_NAME
     */
    public String getFULL_NAME() {
        return FULL_NAME;
    }

    /**
     * @param FULL_NAME the FULL_NAME to set
     */
    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    /**
     * @return the PIN
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * @param PIN the PIN to set
     */
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    /**
     * @return the EMAIL
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     * @param EMAIL the EMAIL to set
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

}