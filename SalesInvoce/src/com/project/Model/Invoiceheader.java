
package com.project.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoiceheader {
    private int Invnum;
    private String customername;
    private Date Invdate;
    private ArrayList<Invoicelines> lines;
   

    public Invoiceheader(int Invnum, String customername, Date Invdate) {
        this.Invnum = Invnum;
        this.customername = customername;
        this.Invdate = Invdate;
        //this.lines = new ArrayList <>(); //eager creation 
        
    }

    public Date getInvdate() {
        return Invdate;
    }

    public void setInvdate(Date Invdate) {
        this.Invdate = Invdate;
    }

    public int getInvnum() {
        return Invnum;
    }

    public void setInvnum(int Invnum) {
        this.Invnum = Invnum;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    @Override
    public String toString(){
        String str = "InvoiceHeader{" + "invNum=" + Invnum + ", customerName=" + customername + ", invDate=" + Invdate + '}';
        for (Invoicelines line : getLines()) {
            str += "\n\t" + line;
        }
        return str;
    }
    
    public ArrayList<Invoicelines> getLines(){
        if (lines == null)
            lines = new ArrayList<>(); //leasy creation
        return lines;
    }

    public void setLines(ArrayList<Invoicelines> lines) {
        this.lines = lines;
    }

    public double getInvTotal() {
        double total = 0.0;
        for (Invoicelines line : getLines()){
            total += line.getLineTotal();
        }
        return total;
    }
    
    public void addInvLine(Invoicelines line) {
        getLines().add(line);
    }   
    public String getDataAsCSV() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getInvnum() + "," + df.format(getInvdate()) + "," + getCustomername();
    }
}  