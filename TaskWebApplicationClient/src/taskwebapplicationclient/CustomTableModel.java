/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskwebapplicationclient;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Iva Stoynova
 */
public class CustomTableModel extends DefaultTableModel {
    CustomTableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }
}
