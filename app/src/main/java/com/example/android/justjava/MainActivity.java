package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.text.NumberFormat;
import java.util.Scanner;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numberOfcoffee=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText namefield =(EditText)findViewById(R.id.Your_Name);
        String name =namefield.getText().toString();
        CheckBox whippedCreamCheckBox =(CheckBox) findViewById(R.id.whipped_cream);
        boolean haswhippedcream =whippedCreamCheckBox.isChecked();
        CheckBox chocolate=(CheckBox) findViewById(R.id.chocolate);
        boolean addchoclate= chocolate.isChecked();
        Log.v("MainActivity","Has  whhipped cream: "+haswhippedcream);
        int price;
            if (whippedCreamCheckBox.isChecked() && chocolate.isChecked()) {
               price=calculatePrice(1,2);
            } else if (whippedCreamCheckBox.isChecked()) {
                price =calculatePrice(1,0);
            } else if (chocolate.isChecked()) {
                price = calculatePrice(0,2);
            } else {
                price = calculatePrice(0,0);
            }
//
//
String priceMessage = createOrderSummary(name,price,haswhippedcream,addchoclate);
//
//        displayMessage(priceMessage);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
    }
    private int calculatePrice(int whippedcream,int choc)
    {
        return numberOfcoffee *(5+whippedcream+choc);
    }
    public void pLus(View view)
    {
if(numberOfcoffee<100)
        numberOfcoffee=numberOfcoffee+1;
        display(numberOfcoffee);
        if(numberOfcoffee==100)
        {
            Toast.makeText(this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
        }

    }
    public void miNus(View view)
    {
if (numberOfcoffee>0) {
    numberOfcoffee = numberOfcoffee - 1;
    display(numberOfcoffee);
}
    }
    private String createOrderSummary(String myname,int price,boolean addwhippedcream, boolean choco)
    {
        String priceMessage ="Details";
        priceMessage +="\nName: "+myname;
        priceMessage +="\nQuantity: "+numberOfcoffee;
        priceMessage +="\nAdd whipped Cream: "+addwhippedcream;
        priceMessage +="\nAdd Chocolate: "+choco;
        priceMessage +="\nTotal: $"+price;
        priceMessage +="\nThank You!" ;
        return priceMessage;
    }
//    public void check(View view)
//    {
//         priceMessage ="Name : "+ name + "\nQuantity : "+numberOfcoffee + "\nTotal : $"+(numberOfcoffee*5) +"\nWhipped cream : Yes"+" \nThank You!";
//    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
//    int incr(int number)
//    {
//        number =number+1;
//        return number;
//    }
//    int descr(int number)
//    {
//        number =number-1;
//        return number;
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }
}