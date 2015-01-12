/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskwebapplicationclient;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.me.people.PersonData;
import org.me.people.PersonDataException;

/**
 *
 * @author Iva Stoynova
 */
public class MainDialog extends javax.swing.JDialog {
    private final String NO_MATCHING_PERSONS_FOUND = "No matching person or persons found";
    /**
     * Creates new form MainDialog
     */
    public MainDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createPersonButton = new javax.swing.JButton();
        findPersonButton = new javax.swing.JButton();
        updatePersonButton = new javax.swing.JButton();
        deletePersonButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        createPersonButton.setText("Create a person");
        createPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPersonButtonActionPerformed(evt);
            }
        });

        findPersonButton.setText("View person");
        findPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPersonButtonActionPerformed(evt);
            }
        });

        updatePersonButton.setText("Update a person");
        updatePersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePersonButtonActionPerformed(evt);
            }
        });

        deletePersonButton.setText("Delete a person");
        deletePersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePersonButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createPersonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(findPersonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatePersonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deletePersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(createPersonButton)
                .addGap(31, 31, 31)
                .addComponent(findPersonButton)
                .addGap(28, 28, 28)
                .addComponent(updatePersonButton)
                .addGap(28, 28, 28)
                .addComponent(deletePersonButton)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPersonButtonActionPerformed
        CreateOrUpdateDialog dialog = new CreateOrUpdateDialog(new javax.swing.JFrame(), true);
        dialog.setUpdatePersonMode(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_createPersonButtonActionPerformed

    private void findPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPersonButtonActionPerformed
        FieldInputDialog dialog = new FieldInputDialog(new javax.swing.JFrame(), true);
        dialog.setFieldInputMode(FieldInputMode.FIND_PERSON);
        String searchName = dialog.showDialog();
        if(searchName != null) {
           List<PersonData> personDataList;
            try {
                personDataList = (List<PersonData>)(Object)findPersons(searchName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
           if(personDataList.size() > 0) {
                PersonListDialog personListDialog = new PersonListDialog(new javax.swing.JFrame(), true, personDataList, true);
                personListDialog.showDialog();
           }
           else {
                JOptionPane.showMessageDialog(this, NO_MATCHING_PERSONS_FOUND);
           }
        }
    }//GEN-LAST:event_findPersonButtonActionPerformed

    private void updatePersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePersonButtonActionPerformed
        FieldInputDialog dialog = new FieldInputDialog(new javax.swing.JFrame(), true);
        dialog.setFieldInputMode(FieldInputMode.UPDATE_PERSON);
        String searchName = dialog.showDialog();
        if(searchName != null) {
            List<PersonData> personDataList;
            try {
                personDataList = (List<PersonData>)(Object)findPersons(searchName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Integer selectedPersonIndex = null;
            if(personDataList.size() > 0) {
                PersonListDialog personListDialog = new PersonListDialog(new javax.swing.JFrame(), true, personDataList, false);
                selectedPersonIndex = personListDialog.showDialog();
            }
            else {
                JOptionPane.showMessageDialog(this, NO_MATCHING_PERSONS_FOUND);
                return;
            }
            if(selectedPersonIndex != null) {
                CreateOrUpdateDialog updateDialog = new CreateOrUpdateDialog(new javax.swing.JFrame(), true);
                updateDialog.setUpdatePersonMode(personDataList.get(selectedPersonIndex));
                updateDialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_updatePersonButtonActionPerformed

    private void deletePersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePersonButtonActionPerformed
        FieldInputDialog dialog = new FieldInputDialog(new javax.swing.JFrame(), true);
        dialog.setFieldInputMode(FieldInputMode.DELETE_PERSON);
        String searchName = dialog.showDialog();
        if(searchName != null) {
            List<PersonData> personDataList;
            try {
                personDataList = (List<PersonData>)(Object)findPersons(searchName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Integer selectedPersonIndex = null;
            if(personDataList.size() > 0) {
                PersonListDialog personListDialog = new PersonListDialog(new javax.swing.JFrame(), true, personDataList, false);
                selectedPersonIndex = personListDialog.showDialog();
            }
            else {
                JOptionPane.showMessageDialog(this, NO_MATCHING_PERSONS_FOUND);
                return;
            }
            if(selectedPersonIndex != null) {
                int result = JOptionPane.showConfirmDialog(this, "Delete selected person data?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {  
                    try {
                        deletePerson(personDataList.get(selectedPersonIndex).getID());
                        JOptionPane.showMessageDialog(this, "Person record deleted successfully"); 
                    } catch (PersonDataException ex) {
                        Logger.getLogger(CreateOrUpdateDialog.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_deletePersonButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainDialog dialog = new MainDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createPersonButton;
    private javax.swing.JButton deletePersonButton;
    private javax.swing.JButton findPersonButton;
    private javax.swing.JButton updatePersonButton;
    // End of variables declaration//GEN-END:variables

    public static String createPerson(org.me.people.PersonData personData) throws PersonDataException {
        org.me.people.PeopleWS_Service service = new org.me.people.PeopleWS_Service();
        org.me.people.PeopleWS port = service.getPeopleWSPort();
        return port.createPerson(personData);
    }

    private static void deletePerson(int id) throws PersonDataException {
        org.me.people.PeopleWS_Service service = new org.me.people.PeopleWS_Service();
        org.me.people.PeopleWS port = service.getPeopleWSPort();
        port.deletePerson(id);
    }

    private static java.util.List<java.lang.Object> findPersons(java.lang.String name) throws PersonDataException {
        org.me.people.PeopleWS_Service service = new org.me.people.PeopleWS_Service();
        org.me.people.PeopleWS port = service.getPeopleWSPort();
        return port.findPersons(name);
    }

    public static String updatePerson(org.me.people.PersonData personData) throws PersonDataException {
        org.me.people.PeopleWS_Service service = new org.me.people.PeopleWS_Service();
        org.me.people.PeopleWS port = service.getPeopleWSPort();
        return port.updatePerson(personData);
    }

}
