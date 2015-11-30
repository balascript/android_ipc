package com.humandroid.android_ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.humandroid.android_ipc.Messengers.MathMessageHandler;

/**
 * Created by Humandroid on 11/28/2015.
 */
public class MathMessengerService extends Service {
    private Messenger mathMessenger= new Messenger(new MathMessageHandler());
    @Override
    public IBinder onBind(Intent intent) {

        return mathMessenger.getBinder();
    }
}
