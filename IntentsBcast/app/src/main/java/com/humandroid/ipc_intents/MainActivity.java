package com.humandroid.ipc_intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText OpA, OpB;
    private Spinner operations;
    private Button Perform,Bcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpA = (EditText) findViewById(R.id.operand_A);
        OpB = (EditText) findViewById(R.id.operand_B);
        operations = (Spinner) findViewById(R.id.operations_spinner);
        Perform = (Button) findViewById(R.id.performBtn);
        Bcast = (Button) findViewById(R.id.bcastButton);
        List<String> operations_list = new ArrayList<String>();
        operations_list.add("Addition");
        operations_list.add("Subtraction");
        operations_list.add("Multiplication");
        operations_list.add("Division");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operations_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operations.setAdapter(dataAdapter);
        operations.setSelection(3);// default Division operation
        Perform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double A = Double.parseDouble(OpA.getText().toString());
                double B = Double.parseDouble(OpB.getText().toString());
                int operation = operations.getSelectedItemPosition() + 1;
                Intent invokeIntent = new Intent("android.intent.action.calculate_with_UI");
                invokeIntent.setType("text/plain");
                invokeIntent.putExtra(Constants.OPERAND_A, A);
                invokeIntent.putExtra(Constants.OPERAND_B, B);
                invokeIntent.putExtra(Constants.OPERATION, operation);
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(invokeIntent, 0);
                boolean isSafeToOPen = activities.size() > 0;
                if (isSafeToOPen) {
                    MainActivity.this.startActivity(invokeIntent);
                }


            }
        });
        Bcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent BcastIntent= new Intent("android.intent.action.calculate_without_UI");
                double A = Double.parseDouble(OpA.getText().toString());
                double B = Double.parseDouble(OpB.getText().toString());
                int operation = operations.getSelectedItemPosition() + 1;
                BcastIntent.putExtra(Constants.OPERAND_A, A);
                BcastIntent.putExtra(Constants.OPERAND_B, B);
                BcastIntent.putExtra(Constants.OPERATION, operation);
                MainActivity.this.sendBroadcast(BcastIntent);
            }
        });

    }
}
