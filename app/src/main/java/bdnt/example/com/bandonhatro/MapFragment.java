package bdnt.example.com.bandonhatro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.HashMap;
import java.util.Map;

import bdnt.example.com.bandonhatro.VolleyListView.Dialog;


public class MapFragment extends Fragment  {

    private GoogleMap mGoogleMap;
    public static View view;
    Map<Marker, String> markerData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub


        if (view != null) {
            Log.i("onCreateView", "!=Null");
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
            /* Setting for map */
            setUpMapIfNeeded();


        } catch (InflateException e) {
        }
        return view;
    }

    private void setUpMapIfNeeded() {
        if (mGoogleMap != null) {
            return;
        }

        mGoogleMap = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mGoogleMap != null) {
            startDemo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public void startDemo() {
        mGoogleMap.setMyLocationEnabled(true);
        Marker marker1 = mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(provider);
        Log.d("null 1", Boolean.toString(myLocation == null));
        // set map type
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();
        Log.i("locaion", Double.toString(latitude) + " " + Double.toString(longitude));
        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Show the current location in Google Map
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        Marker marker2 = mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(21.0413057, 105.7778885)).title("1").snippet("Consider yourself located"));
        Marker marker3 = mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));
        Marker marker4 = mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(21.0436057, 105.7775885)).title("2").snippet("Consider yourself located"));
        markerData = new HashMap<>();
        markerData.put(marker1, "1");
        markerData.put(marker2, "2");
        markerData.put(marker3, "3");
        markerData.put(marker4, "4");
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String id = markerData.get(marker);
                Toast.makeText(getActivity(), "id = " + id, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        final FloatingActionButton mapFilter = (FloatingActionButton) view.findViewById(R.id.mapFilter);
        mapFilter.setShadowColor(Color.WHITE);
        final FloatingActionButton mapList = (FloatingActionButton) view.findViewById(R.id.mapList);
        mapFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "filter", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Dialog.class);
                startActivity(intent);
            }
        });
        mapList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "list", Toast.LENGTH_SHORT).show();
            }
        });

//        //floating button
//        final FloatingActionButton actionButtonParent = (FloatingActionButton)view.findViewById(R.id.multiple_actions);
//        actionButtonParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        final FloatingActionButton actionA = (FloatingActionButton) view.findViewById(R.id.action_a);
//        actionA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(),"pressss ",Toast.LENGTH_SHORT).show();
//            }
//        });
//        final FloatingActionButton actionB = (FloatingActionButton) view.findViewById(R.id.action_b);
    }



}