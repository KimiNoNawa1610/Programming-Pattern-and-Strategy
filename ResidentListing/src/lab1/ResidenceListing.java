/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 * The ResidenceListing class to display a listing of houses
 * getter method of all the Fields of the class
 * setter method for the sale price to display the price that is based on the market
 * toString method to display the class
 * @author votha
 */
public class ResidenceListing {
    private int numbedrooms;
    private String Street_address;
    private int house_area;
    private double sale_price;
    /**
     * The default method of the class
     */
    public ResidenceListing(){
        numbedrooms=3;
        Street_address="8721 Westminster, Garden Grove, CA-92844";
        house_area=1500;
        sale_price=750000;
    }
    /**
     * the getter method to get the numbedrooms of the class
     * @return numbderooms
     */
    public int getNumbedroom(){
        return numbedrooms;
    }
    /**
     * get the Street address of the class
     * @return the street_address
     */
    public String getStreetAddress(){
        return Street_address;
    }
    /**
     * get the area of the house the class
     * @return house_area
     */
    public int getArea(){
        return house_area;
    }
    /**
     * get the sale price of the class
     * @return sale_price
     */
    public double getSale_price(){
        return sale_price;
    }
    /**
     * Setter method to change the sale price of house
     * @param input- the new price of the house
     */
    public void setSale_price(int input){
        sale_price=input;
    }
    /**
     * the method to compute the price per square foot of the house
     * @return pricepersquare
     */
    public double pricePerSquareFoot(){
        double pricepersquare=(house_area/sale_price);
        return pricepersquare;
    }
    /**
     * the toString method to display the information 
     * @return all of the Fields of the class
     */
    @Override
    public String toString(){
        return "House for Sale:"+"\nAddress:"+Street_address+"\narea:"+house_area+"\nbedrooms:"+numbedrooms+
                "\nPrice: $"+sale_price+"\nPrice per square foot: $"+pricePerSquareFoot();
    }
}
