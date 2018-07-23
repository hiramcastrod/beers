package hiram.beers.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class Api {
    static String api="https://api.punkapi.com/v2/";

    public static void GetRequest(String path, final Map<String, String> params, final Map<String, String> headers,
                                  Response.Listener<String> onSuccess, Response.ErrorListener onError,
                                  final Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api+path, onSuccess, onError){
            @Override
            protected Map<String, String> getParams() { return params;}
            @Override
            public  Map<String, String> getHeaders(){
                return headers;
            }
        };
        queue.add(stringRequest);
    }
}
