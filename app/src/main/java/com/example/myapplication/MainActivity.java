package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc = new TipCalculator(0f, 0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View v) {
        EditText billEditText = findViewById(R.id.etNDAmtBill);
        EditText tipEditText = findViewById(R.id.etTipNumber);
        TextView tipTextView = findViewById(R.id.tvTipAmt);
        TextView totalTextView = findViewById(R.id.tvTotalAmt);

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        if (billString.isEmpty() || tipString.isEmpty()) {
            // Handle the case where either the bill amount or tip percentage is empty
            // You may want to display an error message or handle it in a way that makes sense for your application
            return;
        }

        float billAmount = Float.parseFloat(billString);
        int tipPercent = Integer.parseInt(tipString);
        tipCalc.setBill(billAmount);
        tipCalc.setTip(0.01f * tipPercent);
        // Format the results to two decimal places
        String formattedTip = String.format("%.2f", tipCalc.tipAmount());
        String formattedTotal = String.format("%.2f", tipCalc.totalAmount());

        tipTextView.setText(formattedTip);
        totalTextView.setText(formattedTotal);
    }

    public void next(View view) {
        Intent intent = new Intent(this, SplitActivity.class);

        // Pass data to SplitActivity
        intent.putExtra("totalAmount", tipCalc.totalAmount());

        startActivity(intent);
    }
}
