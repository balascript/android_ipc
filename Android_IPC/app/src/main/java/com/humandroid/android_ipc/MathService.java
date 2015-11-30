package com.humandroid.android_ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.humandroid.android_ipc.MathOperations.MathOperations;

/**
 * Created by Humandroid on 11/28/2015.
 */
public class MathService extends Service {

    private final IMathService.Stub mBinder= new IMathService.Stub() {
        @Override
        public double divide(double a, double b) throws RemoteException {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return MathOperations.divide(a,b);
        }

        @Override
        public double perform(double a, double b, int operation) throws RemoteException {
            return MathOperations.getResult(a,b,operation);
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
