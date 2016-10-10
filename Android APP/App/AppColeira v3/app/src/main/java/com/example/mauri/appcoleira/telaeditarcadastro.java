package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class telaeditarcadastro extends AppCompatActivity {
    EditText et_Nome;
    EditText et_endereco;
    EditText et_telefone;
    EditText et_nomepet;
    EditText et_codcoleira;
    Button buttonsalvar;
    Button buttonvoltar;

    /* DADOS*/
    String NomeDono;
    String Endereco;
    String Telefone;
    String NomePet;
    String IdColeira;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaeditarcadastro);

        et_Nome = (EditText)findViewById(R.id.etNome_tec);
        et_endereco = (EditText)findViewById(R.id.etaddress_tec);
        et_telefone = (EditText)findViewById(R.id.ettel_tec);
        et_nomepet = (EditText)findViewById(R.id.etNomePet_tec);
        et_codcoleira = (EditText)findViewById(R.id.etcod_tec);
        buttonsalvar = (Button)findViewById(R.id.botaosalvar_tec);
        buttonvoltar = (Button)findViewById(R.id.botaovoltar_tec);

        //Buscar valores no banco de dados
    }

    public void back_tcad (View view){
        Intent Telacoleira = new Intent(this, telacoleira.class);
        startActivity(Telacoleira);
    }


    //método salvar
    public void salvar_tec (View vire){
        NomeDono = et_Nome.getText().toString();
        Endereco = et_endereco.getText().toString();
        Telefone = et_telefone.getText().toString();
        NomePet = et_nomepet.getText().toString();
        IdColeira = et_codcoleira.getText().toString();

        //Salvar Informações no servidor
        DadosColeira valores = new DadosColeira(IdColeira,NomeDono, Endereco,Telefone, NomePet);

        //Adicionar valores ao banco de dados do Firebase

        DatabaseReference childref = rootRef.child(IdColeira);
        childref.setValue(valores);

    }
}
