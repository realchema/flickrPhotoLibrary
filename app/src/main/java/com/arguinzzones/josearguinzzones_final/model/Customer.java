package com.arguinzzones.josearguinzzones_final.model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable {

    private String customerName;
    private String customerFamilyName;
    private String customerPhoneNumber;
    private int customerSIN;

    private Account customerAccount;

    public Customer() {
    }

    public Customer(String customerName, String customerFamilyName, String customerPhoneNumber, int customerSIN, Account customerAccount) {
        this.customerName = customerName;
        this.customerFamilyName = customerFamilyName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerSIN = customerSIN;
        this.customerAccount = customerAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerFamilyName() {
        return customerFamilyName;
    }

    public void setCustomerFamilyName(String customerFamilyName) {
        this.customerFamilyName = customerFamilyName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getCustomerSIN() {
        return customerSIN;
    }

    public void setCustomerSIN(int customerSIN) {
        this.customerSIN = customerSIN;
    }

    public Account getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Account customerAccount) {
        this.customerAccount = customerAccount;
    }

    @Override
    public String toString() {
        return
                "Name='" + customerName + '\'' +
                ", Family Name='" + customerFamilyName + '\'' +
                ", Phone Number='" + customerPhoneNumber + '\'' +
                ", SIN=" + customerSIN +
                ", Account=" + customerAccount ;
    }

    @Override
    public int compareTo(Object o) {
        Customer otherCountry = (Customer) o;

        return getCustomerFamilyName().compareTo(otherCountry.getCustomerFamilyName());
    }
}
