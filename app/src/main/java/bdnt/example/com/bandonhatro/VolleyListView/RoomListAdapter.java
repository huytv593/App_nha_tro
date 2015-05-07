package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import bdnt.example.com.bandonhatro.AppController;
import bdnt.example.com.bandonhatro.R;

public class RoomListAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private ArrayList<Room> roomItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public RoomListAdapter(Activity activity, ArrayList<Room> roomItems) {
		this.activity = activity;
		this.roomItems = roomItems;
	}

	@Override
	public int getCount() {
		return roomItems.size();
	}

	@Override
	public Object getItem(int location) {
		return roomItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.room_list_item, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.room_thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.room_title);
		TextView address = (TextView) convertView.findViewById(R.id.room_address);
		TextView end_at = (TextView) convertView.findViewById(R.id.room_end_at);
		TextView price = (TextView) convertView.findViewById(R.id.room_price);

		// getting movie data for the row
        Room room = roomItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(room.getImga(), imageLoader);
        Log.i("url",room.getImga());

        // title
		title.setText(room.getTitle());

		// address
		address.setText("Địa chỉ: " + String.valueOf(room.getAddress()));
		
		// end at
		end_at.setText("Ngày hết hạn: "+room.getEnd_at());
		
		// price
		price.setText("Giá: "+String.valueOf(room.getPrice()));

		return convertView;
	}

}