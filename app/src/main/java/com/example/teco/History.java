package com.example.teco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AdapterHistory;
import Model.ModelHistory;
import Server.BaseURL;

public class History extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterHistory adapter;
    ListView list;

    ArrayList<ModelHistory> newsList = new ArrayList<ModelHistory>();
    private RequestQueue mRequestQueue;

    int socketTimeout = 500000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    TextView total;

    SharedPreferences sp;
    public static final String USER_PREF = "USER_PREF" ;
    public static final String userName = "userName";
    public static final String ID = "_id";
    public static final String NamaLengkap = "namaLengkap";

    String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        idUser = sp.getString(ID, "");

        total = (TextView) findViewById(R.id.total);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterHistory(History.this, newsList, sp);
        list.setAdapter(adapter);

        getHistory();

    }

    private void getHistory() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
//        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.history+ idUser, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        hideDialog();
                        try {

//                            String data = response.getString("data");
                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                final ModelHistory history = new ModelHistory();
                                final String _id = jsonObject.getString("_id");
                                final String status = jsonObject.getString("status");
                                final String tgl = jsonObject.getString("created_at");

                                history.setId(_id);
                                history.setStatus(status);
                                history.setTanggal(tgl);

                                newsList.add(history);
                            }

                            total.setText(response.getString("totalBukaPintu"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
//                hideDialog();
            }
        });

        req.setRetryPolicy(policy);
        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }
}