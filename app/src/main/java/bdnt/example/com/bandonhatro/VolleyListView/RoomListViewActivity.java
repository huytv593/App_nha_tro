package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class RoomListViewActivity extends ActionBarActivity {
    ListView listView;
    RoomListAdapter adapter;
    ProgressDialog pDialog;
    ArrayList<Room> roomList;
    TextView nothing_found, result_title;
    Button change_to_mapView, size_result;
    Map<String, String> params;
    //String jsonExample="{\"Nhatro\":[{\"id\":\"1\",\"title\":\"Nhà trọ 1\",\"created_by\":\"1\",\"created_at\":\"2015-04-21\",\"end_at\":\"2015-04-29\",\"price\":\"2000\",\"city\":\"Hà Nội\",\"district\":\"Cầu Giấy\",\"precinct\":\"Dịch Vọng Hậu\",\"street\":\"Xuân Thuỷ\",\"address\":\"114 Xuân Thuỷ\",\"area\":\"20\",\"info\":\"nsdasdnasdnsab nbsdnamsn sadas dn\",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"2\",\"title\":\"Nhà trọ 2\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-04-26\",\"price\":\"3000\",\"city\":\"Hà Nội\",\"district\":\"Cầu Giấy\",\"precinct\":\"Mai Dịch\",\"street\":\"Hồ Tùng Mậu\",\"address\":\"14\",\"area\":\"26\",\"info\":\"đâsdas\",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"3\",\"title\":\"Nhà trọ 3\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-04-30\",\"price\":\"2300\",\"city\":\"Hà Nội\",\"district\":\"Cầu Giấy\",\"precinct\":\"Mai Dịch\",\"street\":\"Phạm Văn Đồng\",\"address\":\"115\",\"area\":\"23\",\"info\":\"13213123 đấ \",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"4\",\"title\":\"Nhà trọ 4\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-04-30\",\"price\":\"3200\",\"city\":\"Hà Nội\",\"district\":\"Cầu Giấy\",\"precinct\":\"Mai Dịch\",\"street\":\"Doãn Kế Thiện\",\"address\":\"30\",\"area\":\"30\",\"info\":\"DKLSANDSLAD\",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"5\",\"title\":\"Nhà trọ 5\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-08-25\",\"price\":\"2000\",\"city\":\"Hà Nội\",\"district\":\"Cầu Giấy\",\"precinct\":\"Dịch Vọng \",\"street\":\"Thành Thái\",\"address\":\"128\",\"area\":\"20\",\"info\":\"dasdsad asd \",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"6\",\"title\":\"Nhà trọ 6\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-08-30\",\"price\":\"2500\",\"city\":\"Hà Nội\",\"district\":\"Ba Đình\",\"precinct\":\"Đội Cấn \",\"street\":\"Ngọc Hà\",\"address\":\"117\",\"area\":\"20\",\"info\":\"asdsa \",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"7\",\"title\":\"Nhà trọ 7\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-08-27\",\"price\":\"2400\",\"city\":\"Hà Nội\",\"district\":\"Đống Đa\",\"precinct\":\"Láng Thượng\",\"street\":\"Pháo Đài Láng\",\"address\":\"23\",\"area\":\"20\",\"info\":\"dksnladnsald\",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null},{\"id\":\"8\",\"title\":\"Nhà trọ 8\",\"created_by\":\"1\",\"created_at\":\"2015-04-22\",\"end_at\":\"2015-08-29\",\"price\":\"2400\",\"city\":\"Hà Nội\",\"district\":\"Đống Đa\",\"precinct\":\"Láng Hạ\",\"street\":\"Huỳnh Thúc Kháng\",\"address\":\"132\",\"area\":\"20\",\"info\":\"jdsald.jpg\",\"imga\":\"1.jpg\",\"imgb\":\"1.jpg\",\"imgc\":\"1.jpg\",\"imgd\":\"1.jpg\",\"long\":null,\"lat\":null}]}";

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
        Log.d("result screen params", Integer.toString(params.size()));

        listView = (ListView) findViewById(R.id.roomList);

        nothing_found = (TextView) findViewById(R.id.nothing_found);
        result_title = (TextView) findViewById(R.id.result_title);
        change_to_mapView = (Button) findViewById(R.id.change_to_mapView);
        size_result = (Button) findViewById(R.id.size_result);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
//        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void searchRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_SEARCH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                Toast.makeText(RoomListViewActivity.this, response, Toast.LENGTH_SHORT).show();
//                Gson gson = new Gson();
//
//                JsonParser jsonParser= new JsonParser();
//                JsonArray roomArray= jsonParser.parse(response).getAsJsonArray();
                roomList = new ArrayList<>();
//                for (JsonElement aRoom: roomArray){
//                    Room room = gson.fromJson(aRoom,Room.class);
//                    roomList.add(room);
//                }
//                Log.d("Test parse json array",roomList.get(0).toString());
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
//                Toast.makeText(RoomListViewActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                 Log.d("ssss", error.getMessage());
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
