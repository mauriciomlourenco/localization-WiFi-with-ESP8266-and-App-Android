package com.example.mauri.appcoleira;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {
    private Button botaoBusca;
    private Button botaoColeira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoBusca = (Button) findViewById(R.id.botaobusca);
        botaoColeira = (Button) findViewById(R.id.botaocoleira);

    }

    public void startTelabusca (View view){
        Intent Telabusca = new Intent(this, BuscarAnimais.class);
        startActivity(Telabusca);
    }

    public void startInterfacecoleira (View view){
        Intent Telacoleira = new Intent(this, TelaColeira.class);
        startActivity(Telacoleira);
    }
}
