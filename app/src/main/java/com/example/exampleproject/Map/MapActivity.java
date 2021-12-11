package com.example.exampleproject.Map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.exampleproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MapActivity extends AppCompatActivity {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    EditText search;
    TextView go;
    List<Address> list;
    ImageView zoomIn, zoomOut;
    GoogleMap gMap;
    LinearLayout animLinear;
    public static final String RESULT = "Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        search = findViewById(R.id.search);
        go = findViewById(R.id.goButton);
//        zoomIn = findViewById(R.id.zoomIn);
//        zoomOut = findViewById(R.id.zoomOut);
        animLinear = findViewById(R.id.linearAnim);


        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);
        list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        } else {

            LoadMap();

        }
//        zoomIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Animation animation= AnimationUtils.loadAnimation(MapActivity.this,R.anim.zoom_in);
//                animLinear.startAnimation(animation);
//            }
//        });
//        zoomOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Animation animation= AnimationUtils.loadAnimation(MapActivity.this,R.anim.zoom_out);
//                animLinear.startAnimation(animation);
//            }
//        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                LoadMap();
            } else {
                Toasty.warning(this, "allow permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void LoadMap() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        } else {
            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                gMap = googleMap;
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                MarkerOptions options = new MarkerOptions().position(latLng);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                //to set type of google map
//                                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                googleMap.addMarker(options);

                                Intent intent = new Intent();
                                intent.putExtra(RESULT, location.getLatitude()+ location.getLongitude());
                            }
                        });
                    }
                }
            });

            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String location = search.getText().toString();
                    if (!location.equals("")) {
                        Geocoder geocoder = new Geocoder(MapActivity.this);
                        try {
                            list = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (list.size() > 0) {
                            Address address = list.get(0);
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            gMap.addMarker(new MarkerOptions().position(latLng).title(location));
                            gMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        } else {
                            Toasty.error(MapActivity.this, "No place found!! Try Again ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        }


    }
}