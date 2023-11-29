package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SplitActivity extends AppCompatActivity {

    private BillSplitter billSplitter = new BillSplitter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        // Retrieve data from Intent
        float totalAmount = getIntent().getFloatExtra("totalAmount", 0.0f);

        // Assuming you have a TextView for total amount in your SplitActivity layout
        TextView totalTextView = findViewById(R.id.tvTotalAmt);
        totalTextView.setText(String.format("%.2f", totalAmount));

    }

    public void splitter(View v) {
        TextView totalEditText = findViewById(R.id.tvTotalAmt); // Assuming you have a TextView for total amount
        EditText peopleEditText = findViewById(R.id.etSplitPeople);
        TextView individualBillTextView = findViewById(R.id.tvSplitBill);

        String totalString = totalEditText.getText().toString();
        String peopleString = peopleEditText.getText().toString();

        if (totalString.isEmpty() || peopleString.isEmpty()) {
            // Handle the case where any of the input fields are empty
            return;
        }

        float totalAmount = Float.parseFloat(totalString);
        int numberOfPeople = Integer.parseInt(peopleString);

        float individualBill = billSplitter.splitBill(totalAmount, numberOfPeople);

        // Format the result to two decimal places
        String formattedIndividualBill = String.format("%.2f", individualBill);
        individualBillTextView.setText(formattedIndividualBill);
    }
}