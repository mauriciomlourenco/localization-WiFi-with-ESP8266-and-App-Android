package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class telacoleira extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telacoleira);
    }

    public void startTelacadastro (View view){
        Intent Telacadastro = new Intent(this, telacadastro.class);
        startActivity(Telacadastro);

    }

    public void startTelainfocoleira (View view){
        Intent Telainfo = new Intent(this, telaInfoColeira.class);
        startActivity(Telainfo);
    }

    //teste, apagar depois
    public void startTelatestefirebase (View view) {
        Intent Telainfo = new Intent(this, testeleiturafirebase.class);
        startActivity(Telainfo);
    }

    public void back (View view){
        Intent Anterior = new Intent(this, MainActivity.class);
        startActivity(Anterior);
    }
}
