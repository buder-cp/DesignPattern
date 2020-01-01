package com.example.facadetoagentmode.proxy;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OkHttpModel implements IHttp{

    private static RequestQueue mQueue;
    private static OkHttpModel mInstance;
    private Context mContext;

    private OkHttpModel(Context context) {
        mContext = context;
        mQueue = Volley.newRequestQueue(context);
    }


    @Override
    public void get(String url, final ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String source = obj.getString("source");
                    callBack.onSuccess(source);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error.toString());
            }
        });

        mQueue.add(stringRequest);
    }
}
