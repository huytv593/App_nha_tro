package bdnt.example.com.bandonhatro;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by NamHoang on 4/21/2015.
 */
public class RoomListAdapter extends ArrayAdapter<Room> {
    Activity context = null;
    ArrayList<Room> listResult = null;
    int layoutId;
    //imageNetwork
    private NetworkImageView networkImageView;
    private ImageLoader imageLoader;

    public RoomListAdapter(Activity context, int layoutId, ArrayList<Room> listResult) {
        super(context, layoutId, listResult);
        this.context = context;
        this.listResult = listResult;
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if (listResult.size() > 0 && position >= 0) {
            networkImageView = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
            final TextView roomName = (TextView) convertView.findViewById(R.id.room_name);
            final TextView roomPrice = (TextView) convertView.findViewById(R.id.room_price);
            final Room room = listResult.get(position);
            roomName.setText(room.getAddress());
            roomPrice.setText(room.getPrice());

            //load image
            imageLoader = ImageRequestQueue.getInstance(this.getContext()).getImageLoader();
            String url = room.getUrlImg();
            imageLoader.get(url,ImageLoader.getImageListener(networkImageView,android.R.mipmap.sym_def_app_icon,android.R.drawable.ic_dialog_alert));
            networkImageView.setImageUrl(url,imageLoader);

        }
        return convertView;
    }
}
