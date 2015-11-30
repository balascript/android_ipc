package com.humandroid.android_ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText OpA,OpB;
    private Spinner operations;
    private Button Perform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinput);
        OpA = (EditText)findViewById(R.id.operand_A);
        OpB = (EditText)findViewById(R.id.operand_B);
        operations=(Spinner)findViewById(R.id.operations_spinner);
        Perform=(Button)findViewById(R.id.performBtn);
        List<String> operations_list = new ArrayList<String>();
        operations_list.add("Addition");
        operations_list.add("Subtraction");
        operations_list.add("Multiplication");
        operations_list.add("Division");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operations_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operations.setAdapter(dataAdapter);
        operations.setSelection(3);// default Division
        Perform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double A= Double.parseDouble(OpA.getText().toString());
                double B=Double.parseDouble(OpB.getText().toString());
                int operation=operations.getSelectedItemPosition()+1;
                Intent invokeIntent=new Intent(MainActivity.this,MathOperationsActivity.class);
                invokeIntent.putExtra(Constants.OPERAND_A,A);
                invokeIntent.putExtra(Constants.OPERAND_B,B);
                invokeIntent.putExtra(Constants.OPERATION,operation);
                MainActivity.this.startActivity(invokeIntent);

            }
        });

    }

    protected void onDestroy() {

        super.onDestroy();
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
