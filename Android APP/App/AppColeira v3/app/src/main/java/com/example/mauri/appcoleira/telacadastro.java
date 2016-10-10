package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class telacadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telacadastro);
    }

    public void startTelanovocadastro (View vire){
        Intent Telanovocadastro = new Intent(this, telanovocadastro.class);
        startActivity(Telanovocadastro);

    }

    public void startTelaeditarcadastro (View view){
        Intent Telaeditarcadastro = new Intent(this, telaeditarcadastro.class);
        startActivity(Telaeditarcadastro);
    }

    public void back_tc (View view){
        Intent Anterior = new Intent(this, telacoleira.class);
        startActivity(Anterior);
    }
}
