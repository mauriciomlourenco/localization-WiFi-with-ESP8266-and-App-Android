package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoFirebase extends AppCompatActivity {

    DadosColeira infosServidor = new DadosColeira();
    IDColeiraCadastrado idcadastrado_bd = new IDColeiraCadastrado();
    DataBaseHandler db = new DataBaseHandler(this);
    DataSnapshot dataBase2;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    DatabaseReference enderecobd = rootRef;
    private ValueEventListener listener;
    TextView textfirebase;

    private double latitude = 0;
    public double longitude = 0;
    public double distancia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_firebase);
        textfirebase = (TextView) findViewById(R.id.tvfirebase);


        //Buscar valores id coleira no banco de dados local
        idcadastrado_bd = db.getIDColeiraBD(1);

        // buscar informações antigas para não sobre-escrever informações de busca erroneamente

        //enderecobd = database.getReference("/" + idcadastrado_bd.getIDColeira());

        enderecobd.child(idcadastrado_bd.getIDColeira()).addValueEventListener( listener = new ValueEventListener() {
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

                //getSnapShot(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("log", "The read failed: " + databaseError.getCode());
            }


        });

    }

    public void startMapInfo(View view){
        Intent telaMapa = new Intent(this, MapsActivity.class);
        startActivity(telaMapa);
    }

    public void backTelaColeira_IF(View view){
        Intent telaColeira = new Intent(this, TelaColeira.class);
        startActivity(telaColeira);
    }


    /*private void getSnapShot(DataSnapshot snapshot) {
        this.dataBase2 = snapshot;
        Log.i("Funcao nova: ", this.dataBase2.getKey());
    }*/

    protected  void onDestroy(){
        super.onDestroy();
        enderecobd.removeEventListener(listener);
    }
}
