package com.humandroid.android_ipc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.humandroid.android_ipc.MathOperations.MathOperations;

/**
 * Created by srbkr on 12/5/2015.
 */
public class MathOperationsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent invoked= intent;
        if(invoked!=null){
            double A= invoked.getDoubleExtra(Constants.OPERAND_A,1.0);
            double B= invoked.getDoubleExtra(Constants.OPERAND_B,1.0);
            int Operation= invoked.getIntExtra(Constants.OPERATION, 1);
           double result= MathOperations.getResult(A,B,Operation);
            Toast.makeText(context,"The result is : "+result,Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(context,"Enough Data not Supplied",Toast.LENGTH_LONG).show();
        }
    }
}
