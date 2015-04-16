package bdnt.example.com.bandonhatro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class MapFragment extends Fragment {
    public static View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
            /* Setting for map */
            GoogleMap mGoogleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            mGoogleMap.setMyLocationEnabled(true);
//            mGoogleMap.getUiSettings().setMapToolbarEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        return view;
    }
}