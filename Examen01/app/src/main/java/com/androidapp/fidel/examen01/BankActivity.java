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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        objectListView=(ListView) findViewById(R.id.lv_customerList);
        objectBankAdapter =new BancAdapter(this);
        objectListView.setAdapter(objectBankAdapter);

        customerArray = this.getIntent().getParcelableArrayListExtra("Array");
        fillDatabase(customerArray);

    }


    @Override
    public void onBackPressed() {
        back();
    }

    public void back(){
        Intent oIntent = new Intent();
        oIntent.putExtra("returnResult",customerArray);
        setResult(MainActivity.RESULT_OK,oIntent);
        finish();
    }

    private void fillDatabase(ArrayList<Customers> lCustomer) {
        objectBankAdapter.clear();

        for(Customers oCustomer:lCustomer) {
            objectBankAdapter.add(oCustomer);

        }

        objectBankAdapter.notifyDataSetChanged();
    }

}
