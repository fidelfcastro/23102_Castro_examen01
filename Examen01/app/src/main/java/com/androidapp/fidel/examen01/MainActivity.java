package com.androidapp.fidel.examen01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.fidel.examen01.Utils.CustomerHelper;
import com.androidapp.fidel.examen01.Utils.DBUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final static String MICONSTANTE="message";
    public final static int RETURN_CODE = 1;
    BancAdapter oBankAdapter;
    Integer turn;

    ArrayList<Customers> customerArray = new ArrayList<Customers>();
    ArrayList<Customers> newCustomerArray = new ArrayList<Customers>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button addCustomer_click = (Button) findViewById(R.id.addCustomerBtn);
        Button calculateQueue_click = (Button) findViewById(R.id.calculateQueue_btn);
        Button delete_click = (Button) findViewById(R.id.btn_delete);

        final EditText txt_customerName = (EditText) findViewById(R.id.customerName_text);
        final EditText txt_operations = (EditText) findViewById(R.id.operationNumber_Text);
        final EditText txt_deleteName = (EditText) findViewById(R.id.deleteText);

        ListView listview = (ListView) findViewById(R.id.listView);
        oBankAdapter = new BancAdapter(this);
        listview.setAdapter(oBankAdapter);

        final CustomerHelper cHelper = new CustomerHelper(getApplicationContext());
        cHelper.open();
        customerArray = cHelper.getAllCustomers();
        cHelper.close();
        oBankAdapter.addAll(customerArray);

        addCustomer_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String customer = txt_customerName.getText().toString();
                Integer operation = Integer.parseInt(txt_operations.getText().toString());
                Customers customers = new Customers(
                        customer,operation
                );
                customerArray.add(customers);
                oBankAdapter.add(customers);
                oBankAdapter.notifyDataSetChanged();
                cHelper.open();
                cHelper.addCustomer(customer, operation, 0);
                cHelper.close();


            }
        });

        delete_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerDelete = txt_deleteName.getText().toString();

                cHelper.open();
                int n = cHelper.deleteCustomer(customerDelete);
                cHelper.close();
                for (int i = 0; i < customerArray.size(); i++) {
                    if (customerArray.get(i).getCustomerName().equals(customerDelete)) {
                        oBankAdapter.remove(customerArray.remove(i));
                        oBankAdapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Name " + customerDelete+ " does not exist in Queue" , Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        calculateQueue_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customerArray.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), BankActivity.class);

                    intent.putExtra("Array", customerArray);
                    startActivity(intent);
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void putCustomers(ArrayList<Customers> lCustomer) {
            oBankAdapter.clear();

        for(Customers oCustomer:lCustomer) {
            oBankAdapter.add(oCustomer);

        }

            oBankAdapter.notifyDataSetChanged();
    }

}
