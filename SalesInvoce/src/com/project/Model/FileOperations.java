/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Model;

import com.project.View.InvoiceFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author 20120
 */
public class FileOperations {
    
    private InvoiceFrame frame;
        private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    
    public FileOperations(InvoiceFrame frame) {
        this.frame = frame;
    }

    public void loadFile ()  {
       frame.getInvoicesList().clear();
       JOptionPane.showMessageDialog(frame, "Please, Select header file", "Attention", JOptionPane.WARNING_MESSAGE);
       JFileChooser openFile = new JFileChooser();
       int result = openFile.showOpenDialog(frame);
       if (result== JFileChooser.APPROVE_OPTION) {
       File headerFile = openFile.getSelectedFile();
        try {
       FileReader headerFr = new FileReader(headerFile);
       BufferedReader headerBr = new BufferedReader(headerFr);
       String headerline = null;
       
       while ((headerline = headerBr.readLine())!= null){
           String[] headerParts = headerline.split(",");
           String invNumStr = headerParts[0];
           String invDateStr = headerParts[1];
           String custName = headerParts[2];
           
           int invNum = Integer.parseInt(invNumStr);
           Date invDate = df.parse(invDateStr);
           
           Invoiceheader inv = new Invoiceheader(invNum, custName, invDate); 
           frame.getInvoicesList().add(inv);
        } 
       
        JOptionPane.showMessageDialog(frame, "Please, Select lines file", "Attention", JOptionPane.WARNING_MESSAGE);
        result = openFile.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File linesFile = openFile.getSelectedFile();
            BufferedReader LinesBr = new BufferedReader(new FileReader(linesFile));
            String linesLine = null;
            while ((linesLine = LinesBr.readLine())!= null) {
                String[] lineParts = linesLine.split(",");
                String invNumStr = lineParts[0];
                String itemName = lineParts[1];
                String itemPriceStr = lineParts[2];
                String itemCountStr = lineParts [3];   
                
                int invNum = Integer.parseInt(invNumStr);
                double itemPrice = Double.parseDouble(itemPriceStr);
                int itemCount = Integer.parseInt(itemCountStr);
                Invoiceheader header = findInvoiceByNum(invNum);
                Invoicelines invLine = new Invoicelines (itemName, itemPrice, itemCount, header);
                header.getLines().add(invLine);
            }   
            frame.setInvoiceHeaderTableModel( new InvoiceHeaderTableModel(frame.getInvoicesList()));
            frame.getInvoicesTable().setModel(frame.getInvoiceHeaderTableModel());
            frame.getInvoicesTable().validate();
           }
                System.out.println("Check");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //displayInvoices();
    } 
    
        
        private Invoiceheader findInvoiceByNum(int InvNum) {
            Invoiceheader header = null;
            for (Invoiceheader inv : frame.getInvoicesList()) {
                if (InvNum == inv.getInvnum()) {
                    header = inv;
                    break;
            }
        }   
        return header;
        
     }
    
    
    
    public void saveData() {
             String headers = "";
        String lines = "";
        for (Invoiceheader header : frame.getInvoicesList()) {
            headers += header.getDataAsCSV();
            headers += "\n";
            for (Invoicelines line : header.getLines()) {
                lines += line.getDataAsCSV();
                lines += "\n";
            }
        }
        JOptionPane.showMessageDialog(frame, "Please, select file to save header data!", "Attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter hFW = new FileWriter(headerFile);
                hFW.write(headers);
                hFW.flush();
                hFW.close();
        JOptionPane.showMessageDialog(frame, "Please, select file to save lines data!", "Attension", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter lFW = new FileWriter(linesFile);
                    lFW.write(lines);
                    lFW.flush();
                    lFW.close();
                }
                 } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
