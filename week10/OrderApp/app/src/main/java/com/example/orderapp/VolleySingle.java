package com.example.orderapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

public class VolleySingle {
    private static VolleySingle mInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;
    private VolleySingle(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }
    public static synchronized VolleySingle getInstance(Context context){
        if (mInstance == null){
            mInstance = new VolleySingle(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
