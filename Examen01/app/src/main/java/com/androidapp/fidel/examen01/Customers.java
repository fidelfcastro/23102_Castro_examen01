package com.androidapp.fidel.examen01;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fidel on 9/8/2017.
 */

public class Customers implements Parcelable {

    private String customerName;
    private Integer operationNumber;

    public Customers(String customerName, Integer operationNumber) {
        this.customerName = customerName;
        this.operationNumber = operationNumber;
    }

    public Customers(Parcel in) {
        customerName=in.readString();
        operationNumber=in.readInt();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(Integer operationNumber) {this.operationNumber = operationNumber;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerName);
        dest.writeInt(operationNumber);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Customers createFromParcel(Parcel in){
            return new Customers(in);
        }
        @Override
        public Customers[] newArray(int size){
            return new Customers[size];
        }
    };
}
