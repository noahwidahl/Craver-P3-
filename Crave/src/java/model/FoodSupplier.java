/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class FoodSupplier {
    private int foodSupplierID;
    private String foodSupplierName;
    private String address;
    private String postNr;
    private String city;
    private String phoneNumber;
    private String externalLink;
    private int stateID;
    private Date addDate;
    private Date modifiedDate;
    private int foodSupplierCategoryID;

    // Constructors
    public FoodSupplier() {
    }

    public FoodSupplier(int foodSupplierID, String foodSupplierName, String address, String postNr, 
                        String city, String phoneNumber, String externalLink, int stateID, 
                        Date addDate, Date modifiedDate, int foodSupplierCategoryID) {
        this.foodSupplierID = foodSupplierID;
        this.foodSupplierName = foodSupplierName;
        this.address = address;
        this.postNr = postNr;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.externalLink = externalLink;
        this.stateID = stateID;
        this.addDate = addDate;
        this.modifiedDate = modifiedDate;
        this.foodSupplierCategoryID = foodSupplierCategoryID;
    }

    // Getters
    public int getFoodSupplierID() {
        return foodSupplierID;
    }

    public String getFoodSupplierName() {
        return foodSupplierName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostNr() {
        return postNr;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public int getStateID() {
        return stateID;
    }

    public Date getAddDate() {
        return addDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public int getFoodSupplierCategoryID() {
        return foodSupplierCategoryID;
    }

    // Setters
    public void setFoodSupplierID(int foodSupplierID) {
        this.foodSupplierID = foodSupplierID;
    }

    public void setFoodSupplierName(String foodSupplierName) {
        this.foodSupplierName = foodSupplierName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setFoodSupplierCategoryID(int foodSupplierCategoryID) {
        this.foodSupplierCategoryID = foodSupplierCategoryID;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "FoodSupplier{" +
                "foodSupplierID=" + foodSupplierID +
                ", foodSupplierName='" + foodSupplierName + '\'' +
                ", address='" + address + '\'' +
                ", postNr='" + postNr + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", externalLink='" + externalLink + '\'' +
                ", stateID=" + stateID +
                ", addDate=" + addDate +
                ", modifiedDate=" + modifiedDate +
                ", foodSupplierCategoryID=" + foodSupplierCategoryID +
                '}';
    }
}
