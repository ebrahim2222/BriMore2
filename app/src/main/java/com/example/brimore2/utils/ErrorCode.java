package com.example.brimore2.utils;

import android.content.Context;
import android.widget.Toast;


public class ErrorCode {
    public static void errorMessage(Context context, int code){
        switch (code){
            case 400:
                Toast.makeText(context, "Bad Request", Toast.LENGTH_SHORT).show();
                break;
            case 401:
                Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(context, "user not found", Toast.LENGTH_SHORT).show();
                break;
            case 408:
                Toast.makeText(context, "Request time out", Toast.LENGTH_SHORT).show();
                break;
            case 500:
                Toast.makeText(context, "server broken", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "UnKnown Error", Toast.LENGTH_SHORT).show();
        }
    }
}
