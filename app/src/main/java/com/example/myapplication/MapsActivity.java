package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        //  tambah koordinat marker
        LatLng sevenmart = new LatLng (-0.922661, 119.864286);
        LatLng user = new LatLng(-0.925517, 119.861373);

        // Marker style
        int tinggi = 100;
        int lebar = 100;

        BitmapDrawable bitmapStart = (BitmapDrawable)getResources().getDrawable(R.drawable.user);
        BitmapDrawable bitmapDes = (BitmapDrawable)getResources().getDrawable(R.drawable.sevenmart);

        Bitmap s = bitmapStart.getBitmap();
        Bitmap d = bitmapDes.getBitmap();

        Bitmap markerStart = Bitmap.createScaledBitmap(s, lebar, tinggi, false);
        Bitmap markerDes = Bitmap.createScaledBitmap(d, lebar, tinggi, false);

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(sevenmart).title("Marker in Seven Mart")
                .snippet("Barangnya Lengkap")
                .icon(BitmapDescriptorFactory.fromBitmap(markerStart)));

        mMap.addMarker(new MarkerOptions().position(user).title("Marker in User")
                .snippet("Palupi Permai")
                .icon(BitmapDescriptorFactory.fromBitmap(markerDes)));

        mMap.addPolyline(new PolylineOptions().add(
                user,
                new LatLng(-0.925517, 119.861373),
                new LatLng(-0.925359, 119.861298),
                new LatLng(-0.924847, 119.861310),
                new LatLng(-0.924279, 119.861362),
                new LatLng(-0.923810, 119.861408),
                new LatLng(-0.923376, 119.861459),
                new LatLng(-0.922617, 119.862006),
                new LatLng(-0.922786, 119.864007),
                sevenmart
        ).width(10)
                .color(Color.RED));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 11.5f));
    }
}