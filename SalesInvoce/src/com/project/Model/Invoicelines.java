
package com.project.Model;

public class Invoicelines {
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private Invoiceheader header;
    

    public Invoicelines(String itemName, double itemPrice, int itemCount, Invoiceheader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }
   

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Invoiceheader getHeader() {
        return header;
    }

    public void setHeader(Invoiceheader header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Invoicelines{" + "itemName=" + itemName + ", itemprice=" + itemPrice + ", itemCount=" + itemCount + '}';
    }  
    public double getLineTot(){
        return itemCount * itemPrice;
    }
  
}
