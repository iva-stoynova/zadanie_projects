/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskwebapplicationclient;

import java.util.List;
import javax.swing.JOptionPane;
import org.me.people.PersonData;

/**
 *
 * @author Iva Stoynova
 */
public class PersonListDialog extends javax.swing.JDialog {
    private String[] tableColumnNames;
    private Object[][] tableData;
    private List<PersonData> personDataList;
    private Integer indexOfSelectedItem = null;
    private boolean hideCancelButton;
    /**
     * Creates new form PersonListDialog
     */
    public PersonListDialog(java.awt.Frame parent, boolean modal, List<PersonData> personDataList, boolean hideCancelButton) {
        super(parent, modal);
        this.hideCancelButton = hideCancelButton;
        this.personDataList = personDataList;
        initComponents();
        tableColumnNames = new String[] {"Full name", "PIN", "Email"};
        tableData = new Object[personDataList.size()][3];
        int i = 0;
        for(PersonData personData : personDataList) {
            tableData[i][0] = personData.getFULLNAME();
            tableData[i][1] = personData.getPIN();
            tableData[i][2] = personData.getEMAIL();            
            i++;
        }     
        personDataTable.setModel(new CustomTableModel(tableData, tableColumnNames));
        if(hideCancelButton) {
            cancelButton.setVisible(false);
            personDataTable.setRowSelectionAllowed(false);
        }
    }
    
    public Integer showDialog() {
        setVisible(true);
        return indexOfSelectedItem;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        personDataTable = new javax.swing.JTable();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        personDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        personDataTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(personDataTable);
        personDataTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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

        jLabel1.setText("Persons matching search criteria:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addGap(52, 52, 52))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(!hideCancelButton) {
            if(personDataTable.getSelectedRow() != -1) {
                indexOfSelectedItem = personDataTable.getSelectedRow();
                setVisible(false);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a person first");
            }
        }
        else {
            setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        indexOfSelectedItem = null;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JTable personDataTable;
    // End of variables declaration//GEN-END:variables
}
