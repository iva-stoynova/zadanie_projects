/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskwebapplicationclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.me.people.PersonData;

/**
 *
 * @author Iva Stoynova
 */
public class CreateOrUpdateDialog extends javax.swing.JDialog {
    PersonData updatePersonData = null;
    /**
     * Creates new form CreateOrUpdateDialog
     */
    public CreateOrUpdateDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void setUpdatePersonMode(PersonData updatePersonData) {
        if(updatePersonData != null) {
            this.updatePersonData = updatePersonData;
            nameTextField.setText(updatePersonData.getFULLNAME());
            String pin = updatePersonData.getPIN();
            if(pin != null && !pin.isEmpty()) {
                pinCheckBox.setSelected(true);
                pinTextField.setEnabled(true);
                pinTextField.setText(pin);
            }
            else {
                pinCheckBox.setSelected(false);
                pinTextField.setEnabled(false);
                pinTextField.setText("");
            }
            String email = updatePersonData.getEMAIL();
            if(email != null && !email.isEmpty()) {
                emailCheckBox.setSelected(true);
                emailTextField.setEnabled(true);
                emailTextField.setText(email);
            }
            else {
                emailCheckBox.setSelected(false);
                emailTextField.setEnabled(false);
                emailTextField.setText("");
            }
            okButton.setText("Update person");
        }
        else {
            okButton.setText("Create person");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        pinTextField = new javax.swing.JTextField();
        emailCheckBox = new javax.swing.JCheckBox();
        pinCheckBox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        jLabel1.setText("Full name:");

        emailTextField.setEnabled(false);

        pinTextField.setEnabled(false);

        emailCheckBox.setText("Email");
        emailCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailCheckBoxActionPerformed(evt);
            }
        });

        pinCheckBox.setText("PIN");
        pinCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinCheckBoxActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(pinTextField)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(emailCheckBox)
                            .addComponent(pinCheckBox)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(pinCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addGap(39, 39, 39))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            PersonData personData = new PersonData();
            personData.setFULLNAME(getPersonFullName());
            personData.setPIN(getPersonPIN());
            personData.setEMAIL(getPersonEmail());
            String message;
            if(updatePersonData != null) {
                personData.setID(updatePersonData.getID());
                message = MainDialog.updatePerson(personData);
                if(message == null) {
                    JOptionPane.showMessageDialog(this, "Person record updated successfully");            
                }
            }
            else {
                message = MainDialog.createPerson(personData);
                if(message == null) {
                    JOptionPane.showMessageDialog(this, "Person record created successfully");
                }
            }
            if(message == null) {
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);    
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_okButtonActionPerformed

    public String getPersonFullName() {
        return nameTextField.getText();     
    }
    
    public String getPersonPIN() {
        String PIN;
        if(pinCheckBox.isSelected()) {
            PIN = pinTextField.getText();
        }
        else {
            PIN = null;
        }
        return PIN;  
    }
    
    public String getPersonEmail() {
        String email;
        if(emailCheckBox.isSelected()) {
            email = emailTextField.getText();
        }
        else {
            email = null;
        }
        return email;        
    }
    
    private void pinCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinCheckBoxActionPerformed
        pinTextField.setEnabled(pinCheckBox.isSelected());
    }//GEN-LAST:event_pinCheckBoxActionPerformed

    private void emailCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailCheckBoxActionPerformed
        emailTextField.setEnabled(emailCheckBox.isSelected());
    }//GEN-LAST:event_emailCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox emailCheckBox;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox pinCheckBox;
    private javax.swing.JTextField pinTextField;
    // End of variables declaration//GEN-END:variables
}
