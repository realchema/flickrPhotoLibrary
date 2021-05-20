package com.arguinzzones.josearguinzzones_final.model;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private int accountNumber;
    private Date accountOpenDate;
    private double balance;

    public Account() {
    }

    public Account(int accountNumber, Date accountOpenDate, double balance) {
        this.accountNumber = accountNumber;
        this.accountOpenDate = accountOpenDate;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(Date accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return
                "Account Number=" + accountNumber +
                ", Account OpenDate=" + accountOpenDate +
                ", Balance=" + balance ;
    }
}
