package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class telanovocadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telanovocadastro);
    }

    public void back_nc (View view){
        Intent Telanovocadastro = new Intent(this, telanovocadastro.class);
        startActivity(Telanovocadastro);
    }

    //Método salvar
}
