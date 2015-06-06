package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

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
    ImageView back;
    TextView nothing_found, result_title;

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


        ;
        params = (Map) getIntent().getSerializableExtra("params");
        back=(ImageView)findViewById(R.id.imvBack1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

        result_title = (TextView) findViewById(R.id.result_title);

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
                    SnackbarManager.show(
                            Snackbar.with(getApplicationContext())
                                    .text("Tìm được "+roomList.size()+" phòng.").color(Color.DKGRAY).textColor(Color.WHITE),RoomListViewActivity.this);
                    listView.setVisibility(View.VISIBLE);
                    adapter = new RoomListAdapter(RoomListViewActivity.this, roomList);
                    listView.setAdapter(adapter);

                } else {
                    SnackbarManager.show(
                    Snackbar.with(getApplicationContext())
                            .text("Không thể tìm được phòng theo yêu cầu.").color(Color.DKGRAY).textColor(Color.WHITE),RoomListViewActivity.this);
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
