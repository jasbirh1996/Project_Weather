package com.weather.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingelton {
    private static VolleySingelton mInstance;
    private RequestQueue requestQueue;
    private  static  Context mContext;

    public VolleySingelton(Context context) {

        mContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingelton getInstance(Context context){
        if(mInstance==null){
            mInstance = new VolleySingelton(context);
        }
        return mInstance;
    }
    public void addToRequestQue(Request request){
        requestQueue.add(request);
    }

}
