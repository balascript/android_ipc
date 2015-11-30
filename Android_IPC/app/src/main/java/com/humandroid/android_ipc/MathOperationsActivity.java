package com.humandroid.android_ipc;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.humandroid.android_ipc.MathOperations.MathOperations;

public class MathOperationsActivity extends AppCompatActivity {
 TextView math_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_operations);
        math_response= (TextView)findViewById(R.id.mathresult_for_other_Apps);
        Intent invoked= getIntent();
        if(invoked!=null){
            double A= invoked.getDoubleExtra(Constants.OPERAND_A,1.0);
            double B= invoked.getDoubleExtra(Constants.OPERAND_B,1.0);
            int Operation= invoked.getIntExtra(Constants.OPERATION,1);
            new LongMathOperation(A,B,Operation).execute();

        }
        else{
            math_response.setText(" Not Enough data supplied for the Long Operation");
        }


    }
    private class LongMathOperation extends AsyncTask<Void,Void,Double>{
        double A,B;
        int op;
        LongMathOperation(double A,double B, int operation){
            this.A=A;
            this.B=B;
            this.op=operation;
        }
        @Override
        protected Double doInBackground(Void... params) {
            return MathOperations.getResult(A,B,op);
        }

        @Override
        protected void onPostExecute(Double v) {
            math_response.setText("The result for your operation is :"+v);
        }
    }
}
