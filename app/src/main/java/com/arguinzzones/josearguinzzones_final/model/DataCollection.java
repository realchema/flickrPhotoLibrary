package com.arguinzzones.josearguinzzones_final.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataCollection implements Serializable {

    private List<Customer> customerArray = new ArrayList<>();

    public List<Customer> getCustomerArray() {
        return customerArray;
    }
}
