/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 20120
 */
public class InvoiceLinesTableModel extends AbstractTableModel {

    private List<Invoicelines> invoiceslines;
    private DateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
    
    public InvoiceLinesTableModel(List<Invoicelines> invoiceslines) {
        this.invoiceslines = invoiceslines;
    }

    public List<Invoicelines> getInvoiceLines() {
        return invoiceslines;
    }

    
    @Override
    public int getRowCount() {
        return invoiceslines.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Item Name";
            case 1:
                return "Item Price";
            case 2:
                return "Item Count";
            case 3:
                return "Line Total";
                default:
                    return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Integer.class;
            case 3:
                return Double.class;
                default:
                    return Object.class;
        }
        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;  
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
     Invoicelines row = invoiceslines.get(rowIndex);
     switch (columnIndex){
            case 0:
                return row.getItemName();
            case 1:
                return row.getItemPrice();
            case 2:
                return row.getItemCount();
            case 3:
                return row.getLineTotal();
                default:
                    return "";
        }
    }

    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
   