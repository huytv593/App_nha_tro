package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.rey.material.widget.Slider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bdnt.example.com.bandonhatro.AppConfig;
import bdnt.example.com.bandonhatro.AppController;
import bdnt.example.com.bandonhatro.R;

public class Dialog extends Activity {
    Button searchOnMap;
    EditText filter_bankinh,filter_gia1,filter_gia2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        filter_gia1=(EditText)findViewById(R.id.filter_gia1);
        filter_gia2=(EditText)findViewById(R.id.filter_gia2);
        searchOnMap=(Button)findViewById(R.id.btnSearchOnMap);
        searchOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchOnMapRequest();
            }
        });

    }
    private void searchOnMapRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_SEARCH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                filter_gia1.getText();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



}
