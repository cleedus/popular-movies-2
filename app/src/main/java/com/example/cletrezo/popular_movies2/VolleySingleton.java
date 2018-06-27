package com.example.cletrezo.popular_movies2;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }


    public  static VolleySingleton getmInstance(){
        if(mInstance==null){
            mInstance = new VolleySingleton();
        }
        return mInstance;

    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

}
