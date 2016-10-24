package com.example.mauri.appcoleira;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class telaInfoColeira extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    DadosColeira infosServidor = new DadosColeira();
    DadosColeira dadoscadastradosbdlocal = new DadosColeira();
    IDColeiraCadastrado idcadastrado_bd = new IDColeiraCadastrado();
    DataBaseHandler db = new DataBaseHandler(this);
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    DatabaseReference enderecobd = rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_info_coleira);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Buscar valores id coleira no banco de dados local
        idcadastrado_bd = db.getIDColeiraBD(1); // tratar mais de um cadastro

        // buscar informações antigas para não sobre-escrever informações de busca erroneamente

        enderecobd = database.getReference("/" + idcadastrado_bd.getIDColeira());

        enderecobd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                infosServidor = dataSnapshot.getValue(DadosColeira.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("log", "The read failed: " + databaseError.getCode());
            }
        });
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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
