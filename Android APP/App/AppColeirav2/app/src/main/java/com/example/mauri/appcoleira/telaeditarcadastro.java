package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class telaeditarcadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaeditarcadastro);
    }

    public void back_tcad (View view){
        Intent Telacoleira = new Intent(this, telacoleira.class);
        startActivity(Telacoleira);
    }

    //Implementar método salvar
}
