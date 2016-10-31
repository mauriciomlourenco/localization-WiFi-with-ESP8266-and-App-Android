package com.example.mauri.appcoleira;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DadosColeira infosServidor = new DadosColeira();
    IDColeiraCadastrado idcadastrado_bd = new IDColeiraCadastrado();
    DataBaseHandler db = new DataBaseHandler(this);
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    DatabaseReference enderecobd = rootRef;
    private ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        //Buscar valores id coleira no banco de dados local
        idcadastrado_bd = db.getIDColeiraBD(1); // tratar mais de um cadastro

        //enderecobd = database.getReference("/" + idcadastrado_bd.getIDColeira());

        enderecobd.child(idcadastrado_bd.getIDColeira()).addValueEventListener(listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Remove estado anterior do mapa caso haja alguma mudança
                mMap.clear();

                infosServidor = dataSnapshot.getValue(DadosColeira.class);

                // Instantiates a new CircleOptions object and defines the center and radius
                LatLng marcador = new LatLng(Double.parseDouble(infosServidor.getLatitude()), Double.parseDouble(infosServidor.getLongitude()));

                Double distancia = Double.parseDouble(infosServidor.getDistancia());
                String dist = String.format("%.3f", distancia);

                mMap.addMarker(new MarkerOptions()
                        .position(marcador)
                        .title("Nome Animal:" + infosServidor.getNomePet())
                        .snippet("Distância Aproximada:" + dist + " m")).showInfoWindow();

                mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador));

                CircleOptions circleOptions = new CircleOptions()
                        .center(marcador)
                        .radius(distancia)
                        .strokeWidth(10)
                        .strokeColor(Color.GREEN)
                        .fillColor(Color.argb(128, 255, 0, 0))
                        .clickable(false);

                // Get back the mutable Circle
                Circle circle = mMap.addCircle(circleOptions);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("log", "The read failed: " + databaseError.getCode());
            }
        });

        /*

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
    }

    protected void onDestroy(){
        super.onDestroy();
        enderecobd.removeEventListener(listener);
    }
}
