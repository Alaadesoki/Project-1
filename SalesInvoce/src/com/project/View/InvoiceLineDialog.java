/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.View;

import com.project.InvoiceFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author 20120
 */
public class InvoiceLineDialog extends JDialog{
    private JTextField ItemNameField;
    private JTextField ItemCountField;
    private JTextField ItemPriceField;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public InvoiceLineDialog(InvoiceFrame frame) {
       itemNameLbl = new JLabel("Item Name");
       ItemNameField = new JTextField(20);
        
        itemCountLbl = new JLabel("Item Count");
        ItemCountField = new JTextField(20);
        
        itemPriceLbl = new JLabel("Item Price");
        ItemPriceField = new JTextField(20);
        
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel"); 
        
        okBtn.setActionCommand("createLineOK");
        cancelBtn.setActionCommand("createLineCancel");
        
        okBtn.addActionListener(frame);
        cancelBtn.addActionListener(frame);
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLbl);
        add(ItemNameField);
        add(itemCountLbl);
        add(ItemCountField);
        add(itemPriceLbl);
        add(ItemPriceField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {return ItemNameField;}
    public JTextField getItemCountField() {return ItemCountField;}
    public JTextField getItemPriceField() {return ItemPriceField;}
}