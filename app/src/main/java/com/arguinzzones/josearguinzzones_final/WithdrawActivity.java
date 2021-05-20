package com.arguinzzones.josearguinzzones_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arguinzzones.josearguinzzones_final.model.Customer;
import com.arguinzzones.josearguinzzones_final.model.DataCollection;

import java.io.Serializable;
import java.util.List;

public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewBalance;

    EditText editTextAmount;

    Button btnWithdraw;

    List<Customer> customerList;

    int clickedItemPosition;

    String balance;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        getMyIntent();
        initializer();


    }

    private void initializer() {
        textViewBalance = findViewById(R.id.textViewShowBalance);
        editTextAmount = findViewById(R.id.editTextEnterAmount);

        btnWithdraw = findViewById(R.id.buttonWithdraw);
        btnWithdraw.setOnClickListener(this);

        textViewBalance.setText(balance);

    }


    private void getMyIntent() {

        Intent intent = getIntent();
          balance  = intent.getStringExtra("balance");
          position = intent.getStringExtra("position");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonWithdraw:
                 withdraw();
                 break;
        }

    }

    private void withdraw() {

        System.out.println("helooo");




        if(editTextAmount.getText().toString().equals("")){
            Toast.makeText(this, "Invalid Amount ", Toast.LENGTH_LONG).show();

        }
        else {
            float userValue =0;
            userValue = Float.valueOf(editTextAmount.getText().toString());
            if (userValue > Double.parseDouble(balance)) {

                Toast.makeText(this, "Invalid Amount(Exceeded!!!)", Toast.LENGTH_LONG).show();

            } else {
                userValue = Float.parseFloat(balance) - userValue;
                balance = String.valueOf(userValue);

                Intent intent = new Intent();
                intent.putExtra("balance", balance);
                intent.putExtra("position", position);
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
}