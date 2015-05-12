package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import bdnt.example.com.bandonhatro.AppConfig;
import bdnt.example.com.bandonhatro.AppController;
import bdnt.example.com.bandonhatro.R;
import bdnt.example.com.bandonhatro.room_details;

public class RoomListViewActivity extends ActionBarActivity {
    ListView listView;
    RoomListAdapter adapter;
    ProgressDialog pDialog;
    ArrayList<Room> roomList;
    TextView nothing_found, result_title;
    Button change_to_mapView, size_result;
    Map<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        searchRequest();
        getSupportActionBar().hide();

    }


    private void init() {
        setContentView(R.layout.activity_room_list_view);

        params = (Map) getIntent().getSerializableExtra("params");

        listView = (ListView) findViewById(R.id.roomList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(RoomListViewActivity.this, room_details.class);
                Room room = roomList.get(position);
                intent.putExtra("roomData",room);
                startActivity(intent);
            }
        });
        nothing_found = (TextView) findViewById(R.id.nothing_found);
        result_title = (TextView) findViewById(R.id.result_title);
        change_to_mapView = (Button) findViewById(R.id.change_to_mapView);
        size_result = (Button) findViewById(R.id.size_result);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

    }

    private void searchRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_SEARCH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                roomList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Nhatro");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Room room = new Room();
                        room.setTitle(object.getString("title"));
                        room.setId(object.getString("id"));
                        room.setCreate_by(object.getString("created_by"));
                        room.setCreate_at(object.getString("created_at"));
                        room.setEnd_at(object.getString("end_at"));
                        room.setPrice(object.getString("price"));
                        room.setCity(object.getString("city"));
                        room.setDistrict(object.getString("district"));
                        room.setPrecinct(object.getString("precinct"));
                        room.setStreet(object.getString("street"));
                        room.setAddress(object.getString("address"));
                        room.setArea(object.getString("area"));
                        room.setInfo(object.getString("info"));
                        room.setImga(object.getString("imga"));
                        room.setImgb(object.getString("imgb"));
                        room.setImgc(object.getString("imgc"));
                        room.setImgd(object.getString("imgd"));
                        roomList.add(room);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (roomList.size() != 0) {
                    size_result.setVisibility(View.VISIBLE);
                    change_to_mapView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    adapter = new RoomListAdapter(RoomListViewActivity.this, roomList);
                    listView.setAdapter(adapter);
                    size_result.setText("Tìm được " + Integer.toString(roomList.size()) + " phòng");
                } else {
                    nothing_found.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


}
