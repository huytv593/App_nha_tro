//package bdnt.example.com.bandonhatro;
//
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
//
//public class ResultSearchActivity extends ActionBarActivity {
//    private ArrayList<Room> listResult = new ArrayList<Room>();
//    RoomListAdapter adapter = null;
//    ListView resultListView = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_result_search);
//        Log.i("INFO","ResultSearchActivity : run here");
//        for(int i=0; i<30; i++) {
//            listResult.add(new Room("http://www.asperyphuket.com/common/images/rooms/standard-room/small/standard-room-1.jpg", "Ngo 1 Pham Van Dong", "1000VND"));
//        }
//        resultListView = (ListView) findViewById(R.id.list_room);
//        adapter = new RoomListAdapter(this, R.layout.list_row, listResult);
//        resultListView.setAdapter(adapter);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_result_search, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
