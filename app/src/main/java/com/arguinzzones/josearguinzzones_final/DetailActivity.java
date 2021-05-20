package com.arguinzzones.josearguinzzones_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arguinzzones.josearguinzzones_final.model.Account;
import com.arguinzzones.josearguinzzones_final.model.Customer;
import com.arguinzzones.josearguinzzones_final.model.DataCollection;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener {

    final static int REQUEST_CODE1 = 1;

    List<Customer> customerList;

    ListView listViewCustomer;

    ArrayAdapter<Customer> listAdapter;

    AlertDialog alertDialog;
    AlertDialog.Builder alertDialog_Builder;

    int clickedItemPosition;
    int clickedItemPosition2 = 0;
    String balance = "";

    String balance2 = "";
    String position2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getMyIntent();
        initializeListView();
        initializerAlertDialog();

    }

    private void initializerAlertDialog(){

        alertDialog_Builder = new AlertDialog.Builder(this);

        alertDialog_Builder.setTitle("Delete element");
        alertDialog_Builder.setMessage("Do you want to delete (Y/N)");

        alertDialog_Builder.setPositiveButton("Yes", this );
        alertDialog_Builder.setNegativeButton("No", this);

        alertDialog = alertDialog_Builder.create();
    }

    private void getMyIntent() {

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("intentExtra");

        Serializable bundleContent = bundle.getSerializable("bundleContent");

        DataCollection dataCollection = (DataCollection)bundleContent;

        customerList = dataCollection.getCustomerArray();

        Collections.sort(customerList);
    }

    private void initializeListView(){

        // 1- Initialize ListView
        listViewCustomer = findViewById( R.id.listViewResultsCustomers);

        // 2- Create an Adapter for ListView
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerList);


        // 3- Assign the Adapter to the list view
        listViewCustomer.setAdapter(listAdapter);


        listViewCustomer.setOnItemClickListener(this);
        listViewCustomer.setOnItemLongClickListener(this);
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                customerList.remove(clickedItemPosition);
                listAdapter.notifyDataSetChanged();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;

        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        balance = String.valueOf(customerList.get(position).getCustomerAccount().getBalance());
        clickedItemPosition2 = position;

        Intent intent = new Intent(this, WithdrawActivity.class);
        intent.putExtra("balance", balance);
        intent.putExtra("position",clickedItemPosition2);
        startActivityForResult(intent, REQUEST_CODE1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE1) {



            if (resultCode == RESULT_OK) {
                balance2  = data.getStringExtra("balance");
                position2 = data.getStringExtra("position");

                Account myAccount = new Account();
                myAccount.setAccountNumber(customerList.get(clickedItemPosition2).getCustomerAccount().getAccountNumber());
                myAccount.setAccountOpenDate(customerList.get(clickedItemPosition2).getCustomerAccount().getAccountOpenDate());
                myAccount.setBalance(Double.parseDouble(balance2));
                customerList.get(clickedItemPosition2).setCustomerAccount(myAccount);
                listAdapter.notifyDataSetChanged();
            }


        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        clickedItemPosition = position;
        alertDialog.show();

        return true;
    }
}