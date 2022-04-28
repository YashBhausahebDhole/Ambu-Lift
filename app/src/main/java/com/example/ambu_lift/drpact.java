package com.example.ambu_lift;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ambu_lift.databinding.ActivityDrpactBinding;

public class drpact extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityDrpactBinding binding;
    Button condor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        condor =findViewById(R.id.drppai);
        binding = ActivityDrpactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        condor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(drpact.this, "Drop Successful", Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(drpact.this,MainActivity.class);
//                startActivity(i);
//            }
//        });
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
        double lat= Double.parseDouble(getIntent().getStringExtra("Lati").toString());
        double longi= Double.parseDouble(getIntent().getStringExtra("Long").toString());
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, longi);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0f));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Patient Location"));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}