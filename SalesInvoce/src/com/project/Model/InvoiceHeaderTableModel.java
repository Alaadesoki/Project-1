/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Model;


import com.project.Model.Invoiceheader;
import com.project.Model.Invoiceheader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 20120
 */
public class InvoiceHeaderTableModel extends AbstractTableModel {

    private List<Invoiceheader> invoicesList;
    private DateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
    
    public InvoiceHeaderTableModel(List<Invoiceheader> invoicesList) {
        this.invoicesList = invoicesList;
    }

    public List<Invoiceheader> getInvoicesList() {
        return invoicesList;
    }

    
    @Override
    public int getRowCount() {
        return invoicesList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "invoice Num";
            case 1:
                return "Customer Name";
            case 2:
                return "Invoice Date";
            case 3:
                return "Invoice Total";
                default:
                    return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
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
     Invoiceheader row = invoicesList.get(rowIndex);
     
     switch (columnIndex){
            case 0:
                return row.getInvnum();
            case 1:
                return row.getCustomername();
            case 2:
                return df.format(row.getInvdate());
            case 3:
                return row.getInvTotal();
                default:
                    return "";
        }
    }
}
    