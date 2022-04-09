/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Controller;

import com.project.Model.FileOperations;
import com.project.Model.InvoiceLinesTableModel;
import com.project.Model.Invoiceheader;
import com.project.Model.Invoicelines;
import com.project.View.InvoiceFrame;
import com.project.View.InvoiceHeaderDialog;
import com.project.View.InvoiceLineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author 20120
 */
public class InvoiceListener implements ActionListener, ListSelectionListener  {
    private InvoiceFrame frame;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private FileOperations fileOperations;
   
    
    public InvoiceListener(InvoiceFrame frame) {
        this.frame = frame;
        this.fileOperations = new FileOperations(frame);
    }
    
    
    @Override
        public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()){
          case "CreateNewInvoice":
              displayNewInvoiceDialog();
              break;
          case "DeleteInvoice":
              deleteInvoice();
              break;
              
          case "CreateNewLine":
              displayNewLineDialog();
                break;
                
          case "DeleteLine":
              deleteLine();
                break;
                
          case "LoadFile":
              fileOperations.loadFile();
              break;
          case "SaveFile":
              fileOperations.saveData();
              break;
          case "createInvCancel":
              createInvCancel();
          break;
          case "createInvOK":
              createInvOK();
          break;
          case "createLineCancel":
              createLineCancel();
          break;
          case "createLineOK":
              createLineOK();
          break;
          
    }
        
}


    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Invoice Selevcted");
        invoicesTableRowSelected();
    }

    private void invoicesTableRowSelected() {
         int selectedRowIndex = frame.getInvoicesTable().getSelectedRow();
        if (selectedRowIndex >= 0) {
            Invoiceheader row = frame.getInvoiceHeaderTableModel().getInvoicesList().get(selectedRowIndex);
            frame.getCustomernameTF().setText(row.getCustomername());
            frame.getInvoicedateTF().setText(df.format(row.getInvdate()));
            frame.getInvoicenumberLB().setText("" + row.getInvnum());
            frame.getInvoicetotalLB().setText("" + row.getInvTotal());
            ArrayList<Invoicelines> lines = row.getLines();
            frame.setInvoiceLinesTableModel(new InvoiceLinesTableModel(lines));
            frame.getInvLinesTable().setModel(frame.getInvoiceLinesTableModel());
            frame.getInvoiceLinesTableModel().fireTableDataChanged();
        }    
    }

    private void displayNewInvoiceDialog() {
        frame.setHeaderDialog(new InvoiceHeaderDialog(frame)); 
        frame.getHeaderDialog().setVisible(true); 
    }
     
    private void displayNewLineDialog() {
        frame.setLineDialog(new InvoiceLineDialog(frame));
        frame.getLineDialog().setVisible(true);
    }

    private void createInvCancel() {
        frame.getHeaderDialog().setVisible(false);
        frame.getHeaderDialog().dispose();
        frame.setHeaderDialog(null);
    }

    private void createInvOK() {
        String CustName = frame.getHeaderDialog().getCustNameField().getText();
        String InvDateStr = frame.getHeaderDialog().getInvDateField().getText();  
        frame.getHeaderDialog().setVisible(false);
        frame.getHeaderDialog().dispose();
        frame.setHeaderDialog(null);
        try {
            Date InvDate = df.parse(InvDateStr);
          int InNum = getNextInvoiceNum(); 
          Invoiceheader Invoiceheader = new Invoiceheader (InNum,CustName, InvDate);
          frame.getInvoicesList().add(Invoiceheader);
          frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
       displayInvoises();
 }       
       private int getNextInvoiceNum() {
           int max = 0;
        for (Invoiceheader header : frame.getInvoicesList()) {
            if (header.getInvnum()> max) {
                max = header.getInvnum();
            }
       }
           
      return max + 1;
     }

    private void createLineCancel() {
        frame.getLineDialog().setVisible(false);
        frame.getLineDialog().dispose();
        frame.setLineDialog(null);
    }

    private void createLineOK() {
        String itemName = frame.getLineDialog().getItemNameField().getText();
        String itemCountStr = frame.getLineDialog().getItemCountField().getText();
        String itemPriceStr = frame.getLineDialog().getItemPriceField().getText();
        frame.getLineDialog().setVisible(false);
        frame.getLineDialog().dispose();
        frame.setLineDialog(null);
        int itemCount = Integer.parseInt(itemCountStr);
        double itemPrice = Double.parseDouble(itemPriceStr);
        int headerIndex = frame.getInvoicesTable().getSelectedRow();
        Invoiceheader invoice = frame.getInvoiceHeaderTableModel().getInvoicesList().get(headerIndex);
        
        Invoicelines invoiceLine = new Invoicelines(itemName, itemPrice, itemCount, invoice);
        invoice.addInvLine(invoiceLine);
        frame.getInvoiceLinesTableModel().fireTableDataChanged();
        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        frame.getInvoicetotalLB().setText("" + invoice.getInvTotal());
        displayInvoises();
    }    

    private void deleteInvoice() {
        int invIndex = frame.getInvoicesTable().getSelectedRow();
        Invoiceheader header = frame.getInvoiceHeaderTableModel().getInvoicesList().get(invIndex);
        frame.getInvoiceHeaderTableModel().getInvoicesList().remove(invIndex);
        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        frame.setInvoiceLinesTableModel(new InvoiceLinesTableModel(new ArrayList<>()));
        frame.getInvLinesTable().setModel(frame.getInvoiceLinesTableModel());
        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
        frame.getCustomernameTF().setText("");
        frame.getInvoicedateTF().setText("");
        frame.getInvoicenumberLB().setText("");
        frame.getInvoicetotalLB().setText("");
        displayInvoises();
    }

    private void deleteLine() {
        int lineIndex = frame.getInvLinesTable().getSelectedRow();
        Invoicelines line = frame.getInvoiceLinesTableModel().getInvoiceLines().get(lineIndex);
        frame.getInvoiceLinesTableModel().getInvoiceLines().remove(lineIndex);
        frame.getInvoiceLinesTableModel().fireTableDataChanged();
        frame.getInvoiceLinesTableModel().fireTableDataChanged();
        frame.getInvoicetotalLB().setText("" + line.getHeader().getInvTotal());
        displayInvoises();
    }

    private void displayInvoises() {
    System.out.println("***************************");
    frame.getInvoicesList().forEach((header) -> {
        System.out.println(header);
        });
        System.out.println("***************************");
    }

    }