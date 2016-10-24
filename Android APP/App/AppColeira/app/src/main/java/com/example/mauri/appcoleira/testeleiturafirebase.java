package com.example.mauri.appcoleira;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class testeleiturafirebase extends AppCompatActivity {

    DadosColeira infosServidor = new DadosColeira();
    IDColeiraCadastrado idcadastrado_bd = new IDColeiraCadastrado();
    DataBaseHandler db = new DataBaseHandler(this);

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    DatabaseReference enderecobd = rootRef;
    TextView textfirebase;

    public double latitude;
    public double longitude;
    public double distancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testeleiturafirebase);
        textfirebase = (TextView) findViewById(R.id.tvfirebase);

        //Buscar valores id coleira no banco de dados local
        idcadastrado_bd = db.getIDColeiraBD(1);

        // buscar informações antigas para não sobre-escrever informações de busca erroneamente

        enderecobd = database.getReference("/" + idcadastrado_bd.getIDColeira());

        enderecobd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                infosServidor = dataSnapshot.getValue(DadosColeira.class);

                latitude = Double.parseDouble(infosServidor.getLatitude());
                longitude = Double.parseDouble(infosServidor.getLongitude());
                distancia = Double.parseDouble(infosServidor.getDistancia());

                textfirebase.setText("Id:" + infosServidor.getIdColeira()
                        +" Nome dono:" + infosServidor.getNomeDonoPet() + " Endereco:" + infosServidor.getEndereco()
                        + " Telefone:" + infosServidor.getTelefone() + " Nome Animal:" + infosServidor.getNomePet()
                        + " status:" + infosServidor.isStatus()
                        + " Latitude:" + infosServidor.getLatitude() + " Longitude:" + infosServidor.getLongitude()
                        + " Ditancia(m):" + infosServidor.getDistancia()
                        + " Data:" + infosServidor.getBuscadatacompleta());

                Log.i("latitude dentro:", String.valueOf(latitude));
                Log.i("longitude dentro:", String.valueOf(longitude));
                Log.i("distância dentro:", String.valueOf(distancia));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("log", "The read failed: " + databaseError.getCode());
            }


        });

        Log.i("latitude fora:", String.valueOf(latitude));
        Log.i("longitude fora:", String.valueOf(longitude));
        Log.i("distância fora:", String.valueOf(distancia));


    }


}
