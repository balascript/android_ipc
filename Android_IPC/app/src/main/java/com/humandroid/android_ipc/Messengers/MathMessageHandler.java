package com.humandroid.android_ipc.Messengers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import com.humandroid.android_ipc.Constants;
import com.humandroid.android_ipc.MathOperations.MathOperations;

/**
 * Created by Humandroid.
 */
public class MathMessageHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        double A=msg.getData().getDouble(Constants.OPERAND_A);
        double B=msg.getData().getDouble(Constants.OPERAND_B);
        int op=msg.getData().getInt(Constants.OPERATION);
        Message response= Message.obtain(null, Constants.MATH_RESPONSE);
        Bundle respData= new Bundle();
        switch (msg.what){
            case Constants.PERFORM:
                respData.putDouble(Constants.Answer, MathOperations.getResult(A,B,op));
                response.setData(respData);
                try {
                    msg.replyTo.send(response);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            default:
                super.handleMessage(msg);
        }

    }
}
