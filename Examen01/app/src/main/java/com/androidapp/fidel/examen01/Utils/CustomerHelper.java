package com.androidapp.fidel.examen01.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.androidapp.fidel.examen01.Customers;

import java.util.ArrayList;

/**
 * Created by fidel on 9/22/2017.
 */

public class CustomerHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] CUSTOMERS_TABLE_COLUMNS=
        {
            DBUtils.CUSTOMER_ID,
            DBUtils.CUSTOMER_NAME,
            DBUtils.CUSTOMER_OPERATIONS,
            DBUtils.CUSTOMER_POSITION
        };

    public CustomerHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Customers addCustomer(String name, int operations, int position) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_NAME,name);
        values.put(DBUtils.CUSTOMER_OPERATIONS,operations);
        values.put(DBUtils.CUSTOMER_POSITION,position);

        long lCustomerId = database.insert(DBUtils.CUSTOMER_TABLE_NAME,null,values);

        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE_NAME,
                CUSTOMERS_TABLE_COLUMNS,DBUtils.CUSTOMER_ID+ " = " + lCustomerId,
                null,null,null,null);
        cursor.moveToFirst();
        Customers oCustomer = parseCustomer(cursor);
        cursor.close();
        return oCustomer;
    }

    public int deleteCustomer(String customerName){
        return database.delete(DBUtils.CUSTOMER_TABLE_NAME, DBUtils.CUSTOMER_NAME + " = '" + customerName + "'", null);
    }

    public ArrayList<Customers> getAllCustomers() {
        ArrayList<Customers> oCustomers = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE_NAME, CUSTOMERS_TABLE_COLUMNS, null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            oCustomers.add(parseCustomer(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oCustomers;
    }

    private Customers parseCustomer(Cursor cursor) {
        Customers oCustomer = new Customers();
        oCustomer.setCustomerName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_NAME)));
        oCustomer.setOperationNumber(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_OPERATIONS)));
        return oCustomer;
    }

    //Tarea 5 y fecha de entrega
    //Tarea implementar metodos en banpatito
    //Cuando se cierre y abra aplicacion deben de existir los mismos Customers
}
