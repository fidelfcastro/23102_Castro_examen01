package com.androidapp.fidel.examen01;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BankActivity extends AppCompatActivity {
    BancAdapter objectBankAdapter;
    ListView objectListView;
    ArrayList<Customers> customerArray;
    String nameDelete;
    int operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        objectListView=(ListView) findViewById(R.id.lv_customerList);
        objectBankAdapter =new BancAdapter(this);
        objectListView.setAdapter(objectBankAdapter);

        customerArray = this.getIntent().getParcelableArrayListExtra("Array");
        generateQueue(customerArray);

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private void generateQueue(ArrayList<Customers> lCustomer) {
        objectBankAdapter.clear();

        for(Customers cust : lCustomer){
            operation += cust.getOperationNumber()*lCustomer.size();
        }

        while(operation>=0) {
            for (Customers oCustomer : lCustomer) {
                if (oCustomer.getOperationNumber() > 0) {
                    int currentOperation = oCustomer.getOperationNumber();
                    oCustomer.setOperationNumber(currentOperation - 1);
                    objectBankAdapter.add(new Customers(oCustomer.getCustomerName(), oCustomer.getOperationNumber()+1));
                }


                operation--;
            }
        }

        objectBankAdapter.notifyDataSetChanged();






    }

}
