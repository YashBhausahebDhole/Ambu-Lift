package com.example.ambu_lift;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class emerlocation extends AppCompatActivity {
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    Button conloc;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emerlocation);
        conloc=findViewById(R.id.conloc);


        this.supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        this.client = LocationServices.getFusedLocationProviderClient((Activity) this);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 44);
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            public void onSuccess(final Location location) {
                if (location != null) {
                    emerlocation.this.supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        public void onMapReady(GoogleMap googleMap) {

                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Patient Location");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                            googleMap.addMarker(options);


                            conloc.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String lon=(String.valueOf(location.getLongitude()));
                                    String lat=(String.valueOf(location.getLatitude()));
                                    Toast.makeText(emerlocation.this, "Patient Location Fetched Successfully"+"\nLatitude= "+lat+"\nLongitude= "+lon, Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(emerlocation.this,EmergencyBook.class);
                                    intent.putExtra("Lati",lat);
                                    intent.putExtra("Longi",lon);
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44 && grantResults.length > 0 && grantResults[0] == 0) {
            getCurrentLocation();
        }
    }
}
