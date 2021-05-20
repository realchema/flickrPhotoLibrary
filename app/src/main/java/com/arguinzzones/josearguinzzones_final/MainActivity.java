package com.arguinzzones.josearguinzzones_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arguinzzones.josearguinzzones_final.model.Account;
import com.arguinzzones.josearguinzzones_final.model.Customer;
import com.arguinzzones.josearguinzzones_final.model.DataCollection;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextANumber, editTextADate, editTextABalance, editTextCName, editTextCFamily, editTextCPhone, editTextCSIN;

    Button btnAdd, btnFind, btnRemove, btnUpdate, btnClear, btnShowAll;

    DataCollection dataCollection;

    int getIndex;
    boolean findFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializer();
        initializeData();
    }

    private void initializer(){
        editTextANumber = findViewById(R.id.editTextAccountNumber);
        editTextADate = findViewById(R.id.editTextOpenDate);
        editTextABalance = findViewById(R.id.editTextBalance);
        editTextCName = findViewById(R.id.editTextName);
        editTextCFamily = findViewById(R.id.editTextFamilyName);
        editTextCPhone = findViewById(R.id.editTextPhone);
        editTextCSIN = findViewById(R.id.editTextSIN);

        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);

        btnFind = findViewById(R.id.buttonFind);
        btnFind.setOnClickListener(this);

        btnRemove = findViewById(R.id.buttonRemove);
        btnRemove.setOnClickListener(this);

        btnUpdate = findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(this);

        btnClear = findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(this);

        btnShowAll = findViewById(R.id.buttonShowAll);
        btnShowAll.setOnClickListener(this);

        dataCollection = new DataCollection();
    }

    private void initializeData(){
        Customer myCustomer = new Customer();
        myCustomer.setCustomerName("Jose");
        myCustomer.setCustomerFamilyName("Arguinzzones");
        myCustomer.setCustomerPhoneNumber("5144319835");
        myCustomer.setCustomerSIN(123456);

        Account myAccount = new Account();
        myAccount.setAccountNumber(111111);
        myAccount.setAccountOpenDate(new Date(2019, 06, 20));
        myAccount.setBalance(5000);

        myCustomer.setCustomerAccount(myAccount);

        dataCollection.getCustomerArray().add(myCustomer);

        Customer myCustomer1 = new Customer();
        myCustomer1.setCustomerName("Daniela");
        myCustomer1.setCustomerFamilyName("Boscan");
        myCustomer1.setCustomerPhoneNumber("5144319000");
        myCustomer1.setCustomerSIN(123457);

        Account myAccount1 = new Account();
        myAccount1.setAccountNumber(222222);
        myAccount1.setAccountOpenDate(new Date(2018, 03, 10));
        myAccount1.setBalance(1000);

        myCustomer1.setCustomerAccount(myAccount1);

        dataCollection.getCustomerArray().add(myCustomer1);

        Customer myCustomer2 = new Customer();
        myCustomer2.setCustomerName("Lucca");
        myCustomer2.setCustomerFamilyName("Arguinzzones");
        myCustomer2.setCustomerPhoneNumber("5144319111");
        myCustomer2.setCustomerSIN(123458);

        Account myAccount2 = new Account();
        myAccount2.setAccountNumber(333333);
        myAccount2.setAccountOpenDate(new Date(2020, 01, 01));
        myAccount2.setBalance(2000);

        myCustomer2.setCustomerAccount(myAccount2);

        dataCollection.getCustomerArray().add(myCustomer2);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonAdd:
                try {
                    add();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonFind:
                find();
                break;
            case R.id.buttonRemove:
                remove();
                break;

            case R.id.buttonUpdate:
                update();
                break;
            case R.id.buttonClear:
                clear();
                break;
            case R.id.buttonShowAll:
                showAll();
                break;
        }

    }

    private void showAll() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleContent", dataCollection);

        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra("intentExtra", bundle);

        startActivity(intent);
    }

    private void clear() {
        editTextANumber.setText("");
        editTextADate.setText("");
        editTextABalance.setText("");
        editTextCName.setText("");
        editTextCFamily.setText("");
        editTextCPhone.setText("");
        editTextCSIN.setText("");
    }

    private void update() {


        if(findFirst == false){
            Toast.makeText(this, "You have to find first the Customer", Toast.LENGTH_LONG).show();
        }
        else {


            if (editTextCName.getText().toString().equals("") || editTextCFamily.getText().toString().equals("") || editTextCPhone.getText().toString().equals("") || editTextCSIN.getText().toString().equals("") ||
                    editTextANumber.getText().toString().equals("") || editTextADate.getText().toString().equals("") || editTextABalance.getText().toString().equals("")) {
                System.out.println("hello111");
                Toast.makeText(this, "A Field is Empty", Toast.LENGTH_LONG).show();
            } else {


                dataCollection.getCustomerArray().get(getIndex).setCustomerName(editTextCName.getText().toString());
                dataCollection.getCustomerArray().get(getIndex).setCustomerFamilyName(editTextCFamily.getText().toString());
                dataCollection.getCustomerArray().get(getIndex).setCustomerPhoneNumber(editTextCPhone.getText().toString());
                dataCollection.getCustomerArray().get(getIndex).setCustomerSIN(Integer.parseInt(editTextCSIN.getText().toString()));

                Account myAccount = new Account();
                myAccount.setAccountNumber(Integer.parseInt(editTextANumber.getText().toString()));
                String dDate = editTextADate.getText().toString();
                myAccount.setAccountOpenDate(new Date(editTextADate.getText().toString()));
                myAccount.setBalance(Double.parseDouble(editTextABalance.getText().toString()));

                dataCollection.getCustomerArray().get(getIndex).setCustomerAccount(myAccount);
                Toast.makeText(this, "Customer info updated", Toast.LENGTH_SHORT).show();
                findFirst = false;
            }
        }

    }

    private void remove() {

        if(editTextCSIN.getText().toString().equals("")){
            Toast.makeText(this, "Enter Customer SIN Number to Remove", Toast.LENGTH_SHORT).show();
        }
        else {

            if (find() >= 0) {
                dataCollection.getCustomerArray().remove(find());
                Toast.makeText(this, "Customer on Screen removed", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int find() {

        if(editTextCSIN.getText().toString().equals("")){
            Toast.makeText(this, "Enter Customer SIN Number to Search", Toast.LENGTH_SHORT).show();
        }
        else {
            //----------------------------------------- Detect client number in edit Text field
            int customerSIN = Integer.parseInt(editTextCSIN.getText().toString());
            boolean found = false;

            //----------------------------------------- Iterate on clint array
            for (Customer oneCustomer : dataCollection.getCustomerArray()) {
                //----------------------------------------- Check each client for client number
                if (oneCustomer.getCustomerSIN() == customerSIN) {
                    found = true;

                    editTextCName.setText(oneCustomer.getCustomerName());
                    editTextCFamily.setText(oneCustomer.getCustomerFamilyName());
                    editTextCPhone.setText(oneCustomer.getCustomerPhoneNumber());
                    String sIN = String.valueOf(oneCustomer.getCustomerSIN());
                    editTextCSIN.setText(sIN);
                    String accountNumber = String.valueOf(oneCustomer.getCustomerAccount().getAccountNumber());
                    editTextANumber.setText(accountNumber);
                    String accountOpenDate = oneCustomer.getCustomerAccount().getAccountOpenDate().toString();
                    editTextADate.setText(accountOpenDate);
                    String accountBalance = String.valueOf(oneCustomer.getCustomerAccount().getBalance());
                    editTextABalance.setText(accountBalance );
                    getIndex =dataCollection.getCustomerArray().indexOf(oneCustomer);
                    findFirst = true;
                    return dataCollection.getCustomerArray().indexOf(oneCustomer);

                }
            }

            if (!found) {
                Toast.makeText(this, "Customer not found", Toast.LENGTH_SHORT).show();
            }
        }

            return -1;
    }

    private void add() throws ParseException {

        String [] arrayMessage = {"Enter Account Number", "Enter Open Date", "Enter Balance", "Enter Name", "Enter Family Name", "Enter Phone" , "Enter SIN"};

        String toastMessage ="";

        int checkFieldState = 0;

        if(editTextCName.getText().toString().equals("") || editTextCFamily.getText().toString().equals("") || editTextCPhone.getText().toString().equals("") || editTextCSIN.getText().toString().equals("")||
                editTextANumber.getText().toString().equals("") || editTextADate.getText().toString().equals("")|| editTextABalance.getText().toString().equals("") ) {
            System.out.println("hello111");
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_LONG).show();
        }

        else {
            System.out.println("hello222");
            String name = editTextCName.getText().toString();
            String familyName = editTextCFamily.getText().toString();
            String phoneNumber = editTextANumber.getText().toString();
            String dDate = editTextADate.getText().toString();
            Customer myCustomer = new Customer();

            myCustomer.setCustomerName(name);

            myCustomer.setCustomerFamilyName(familyName);

            myCustomer.setCustomerPhoneNumber(phoneNumber);

            myCustomer.setCustomerSIN(Integer.parseInt(editTextCSIN.getText().toString()));

            Account myAccount = new Account();

//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-d");
//            Date date = formatter.parse(dDate);

            myAccount.setAccountNumber(Integer.parseInt(editTextANumber.getText().toString()));

            try {
                myAccount.setAccountOpenDate(new Date(dDate));
            }
            catch (Exception exception){

                myAccount.setAccountOpenDate(new Date(2000, 01, 01));
            }


            myAccount.setBalance(Double.parseDouble(editTextABalance.getText().toString()));

            myCustomer.setCustomerAccount(myAccount);

            dataCollection.getCustomerArray().add(myCustomer);

            clear();

            Toast.makeText(this, "Customer Has been Added", Toast.LENGTH_LONG).show();

        }

    }
}