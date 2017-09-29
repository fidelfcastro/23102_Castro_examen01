package com.androidapp.fidel.examen01;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.IdRes;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.TextView;

        import android.content.Intent;
        import android.graphics.Movie;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.ListView;

        import com.androidapp.fidel.examen01.Customers;

        import java.util.ArrayList;


public class BancAdapter extends ArrayAdapter<Customers> {
    public BancAdapter(Context context){
        super(context,R.layout.customers_layout,R.id.txtCustomerName);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView=super.getView(position,convertView,parent);
        TextView txtCustomerName=(TextView) objectView.findViewById(R.id.txtCustomerName);
        TextView txtOperations=(TextView) objectView.findViewById(R.id.txtOperationNumber);


        Customers objectCustomer = this.getItem(position);
        txtCustomerName.setText((position+1) + ": " + objectCustomer.getCustomerName()+" - ");
        txtOperations.setText(String.valueOf(objectCustomer.getOperationNumber()));


        return objectView;
    }
}
