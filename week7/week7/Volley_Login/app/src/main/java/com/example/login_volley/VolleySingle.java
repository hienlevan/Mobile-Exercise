package com.example.login_volley;

import android.content.Context;
import android.security.identity.InvalidRequestMessageException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingle {
    private static VolleySingle mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    // khoi tao constructor
    public VolleySingle(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    // ham lay context
    public static synchronized VolleySingle getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleySingle(context);
        }
        return mInstance;
    }

    // ham RequestQueue
    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(
                    mCtx.getApplicationContext()
            );
        }
        return mRequestQueue;
    }

    // ham addtoRequest
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
