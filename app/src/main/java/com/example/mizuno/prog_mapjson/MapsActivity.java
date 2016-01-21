package com.example.mizuno.prog_mapjson;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AsyncTaskGetJson taskGetJson = new AsyncTaskGetJson(this);
        taskGetJson.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latlng = new LatLng(34.738249,137.9592173);
        mMap.addMarker(new MarkerOptions().position(latlng).title("静岡理工科大学"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
    }

    public void mapLocation(double geo[]){

        mMap.clear();
        for(int i = 0; i < geo.length; i+=2) {
            LatLng latlng = new LatLng(geo[i], geo[i+1]);
            mMap.addMarker(new MarkerOptions().position(latlng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 14));
        }

    }
}
