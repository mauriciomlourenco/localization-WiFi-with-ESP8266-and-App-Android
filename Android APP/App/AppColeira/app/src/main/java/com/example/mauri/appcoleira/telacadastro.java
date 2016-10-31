package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
    }

    public void startTelanovocadastro (View vire){
        Intent Telanovocadastro = new Intent(this, TelaNovoCadastro.class);
        startActivity(Telanovocadastro);

    }

    public void startTelaeditarcadastro (View view){
        Intent Telaeditarcadastro = new Intent(this, TelaEditarCadastro.class);
        startActivity(Telaeditarcadastro);
    }

    public void back_tc (View view){
        Intent Anterior = new Intent(this, TelaColeira.class);
        startActivity(Anterior);
    }
}
