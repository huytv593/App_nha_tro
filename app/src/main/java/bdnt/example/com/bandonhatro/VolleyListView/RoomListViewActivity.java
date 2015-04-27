package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bdnt.example.com.bandonhatro.AppConfig;
import bdnt.example.com.bandonhatro.AppController;
import bdnt.example.com.bandonhatro.R;

public class RoomListViewActivity extends ActionBarActivity {
    ListView listView;
    RoomListAdapter adapter;
    ProgressDialog pDialog;
    ArrayList<Room>roomList;
    TextView nothing_found,result_title;
    Button change_to_mapView,size_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list_view);
        Intent intent = getIntent();
        final HashMap<String, String> params = (HashMap<String, String>)intent.getSerializableExtra("params");
        String stringParams = ">>" + params.get("city") + " || " + params.get("district") + " || " + params.get("precinct")
                + " || " + params.get("street") + " || " + params.get("minSquare") + " || " + params.get("maxSquare") + " || "
                + params.get("minPrice") + " || " + params.get("maxPrice") + "<<";
        Log.i("params",stringParams);

        listView = (ListView) findViewById(R.id.roomList);
        roomList=new ArrayList<>();
        adapter = new RoomListAdapter(RoomListViewActivity.this, roomList);
        listView.setAdapter(adapter);
        nothing_found=(TextView)findViewById(R.id.nothing_found);
        result_title=(TextView)findViewById(R.id.result_title);
        change_to_mapView=(Button)findViewById(R.id.change_to_mapView);
        size_result=(Button)findViewById(R.id.size_result);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonArrayRequest movieReq = new JsonArrayRequest(Request.Method.POST,AppConfig.URL_SEARCH,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("respone", response.toString()+" // "+response.length());
                        hidePDialog();
                        if(response.length()==0){
                            listView.setVisibility(View.GONE);
                            nothing_found.setVisibility(View.INVISIBLE);
                            change_to_mapView.setVisibility(View.GONE);
                            size_result.setVisibility(View.GONE);
                            result_title.setVisibility(View.GONE);
                        }else {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Room room = new Room();
                                    room.setTitle(obj.getString("title"));
                                    room.setImga(obj.getString("image"));
                                    room.setAddress(obj.getString("address"));
                                    room.setPrice(obj.getString("price"));
                                    // adding room to rooms array
                                    roomList.add(room);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error",  error.getMessage());
                hidePDialog();

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String,String>mMap=new HashMap<>();
                mMap.put("city", params.get("city"));
                mMap.put("district", params.get("district"));
                mMap.put("precinct",params.get("precinct"));
                mMap.put("street", params.get("street"));
                mMap.put("minSquare", params.get("minSquare"));
                mMap.put("maxSquare", params.get("maxSquare"));
                mMap.put("minPrice", params.get("minPrice"));
                mMap.put("maxPrice", params.get("maxPrice"));
                return mMap;
            }


        };


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
