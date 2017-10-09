package edu.uw.listviewdemo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by anirudhsubramanyam on 10/9/17.
 */

public class RequestSingleton {

    private RequestQueue queue;

    private RequestSingleton(Context ctx){
        Volley.newRequestQueue(ctx.getApplicationContext());
    }

    public static RequestSingleton newInstance(Context ctx){
        return new RequestSingleton(ctx);
    }

    public RequestQueue getQueue(){
        return this.queue;
    }
}
