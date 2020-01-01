package com.example.facadetoagentmode.facade;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.facadetoagentmode.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class FacadeNetWork {

    public interface Callback<T> {
        void onSuccess(T respone);
        void onFailed(String failed);
    }

    private static RequestQueue mQueue;
    private static FacadeNetWork mInstance;
    private Context mContext;

    private FacadeNetWork(Context context) {
        mContext = context;
        mQueue = Volley.newRequestQueue(context);
    }

    public static FacadeNetWork getInstance(Context context) {
        if (mInstance == null) {
            synchronized (FacadeNetWork.class) {
                if (mInstance == null) {
                    mInstance = new FacadeNetWork(context);
                }
            }
        }
        return mInstance;
    }
    public void get(final String url, final Callback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String source = obj.getString("source");
                    callback.onSuccess(source);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed(error.toString());
            }
        });

        mQueue.add(stringRequest);
    }
}
