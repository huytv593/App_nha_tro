package bdnt.example.com.bandonhatro;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import bdnt.example.com.bandonhatro.VolleyListView.Room;


public class room_details extends ActionBarActivity {

    TextView tv_roomDetailTitle, tv_roomDetailSquare, tv_roomDetailPrice, tv_roomDetailAddress, tv_roomDetailEndAt, tv_roomDetailInfo;
    Gallery gallery;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        getSupportActionBar().hide();
        initial();
        deployment();

    }

    private void initial() {
        tv_roomDetailTitle = (TextView) findViewById(R.id.result_title);
        tv_roomDetailSquare = (TextView) findViewById(R.id.tvDientich);
        tv_roomDetailPrice = (TextView) findViewById(R.id.tvGia);
        tv_roomDetailAddress = (TextView) findViewById(R.id.tvDiachi);
        tv_roomDetailEndAt = (TextView) findViewById(R.id.tvNgayhethan);
        tv_roomDetailInfo = (TextView) findViewById(R.id.tvMota);
        gallery = (Gallery) findViewById(R.id.gallery);

    }

    private void deployment() {
        Room roomData = (Room) getIntent().getSerializableExtra("roomData");
        Log.d("pass room data", roomData.toString());
        tv_roomDetailTitle.setText(roomData.getTitle());
        tv_roomDetailSquare.setText(roomData.getArea());
        tv_roomDetailPrice.setText(roomData.getPrice());
        tv_roomDetailAddress.setText(roomData.getAddress());
        tv_roomDetailInfo.setText(roomData.getInfo());
        tv_roomDetailEndAt.setText(roomData.getEnd_at());

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        final String images[]={roomData.getImga(),roomData.getImgb(),roomData.getImgc(),roomData.getImgd()};
        gallery.setAdapter(new ImageRoomAdapter(room_details.this,images));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(room_details.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.room_image_zoom_out);
                NetworkImageView roomImageInZoomOut = (NetworkImageView) dialog.findViewById(R.id.roomImageInZoomOut);
              roomImageInZoomOut.setImageUrl(images[position],imageLoader);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_room_details, menu);
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

    private class ImageRoomAdapter extends BaseAdapter {
        private Context context;
        String[] images;
        private LayoutInflater inflater;
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        public ImageRoomAdapter(Context context, String[] images) {
            this.context = context;
            this.images = images;

        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (inflater == null)
                inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                convertView = inflater.inflate(R.layout.galery_item, null);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            NetworkImageView roomDetails_images = (NetworkImageView) convertView
                    .findViewById(R.id.roomDetails_images);
            roomDetails_images.setImageUrl(images[position], imageLoader);
            return convertView;
        }
    }
}
