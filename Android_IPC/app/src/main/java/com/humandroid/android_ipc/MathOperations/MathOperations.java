package com.humandroid.android_ipc.MathOperations;

import com.humandroid.android_ipc.Constants;

/**
 * Created by Humandroid on 11/28/2015.
 */
public class MathOperations {
    public static double divide(double a, double b){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a/b;
    }
    public  static double add(double a, double b){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a+b;
    }
    public  static double subtract(double a, double b){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a-b;
    }
    public  static double multiply(double a, double b){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a*b;
    }

    public static double getResult(double a, double b, int operation) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (operation){
            case Constants.ADD:
                return a+b;
            case Constants.SUB:
                return a-b;
            case Constants.MULTIPLYY:
                return a*b;
            case Constants.DIVIDE:
                return a/b;
            default:
                return 0;

        }

    }
}
