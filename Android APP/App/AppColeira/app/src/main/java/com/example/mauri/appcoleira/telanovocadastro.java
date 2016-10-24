package com.example.mauri.appcoleira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class telanovocadastro extends AppCompatActivity {
    EditText et_NomeD;
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
    IDColeiraCadastrado idcadastro = new IDColeiraCadastrado();

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    DataBaseHandler db = new DataBaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telanovocadastro);

        et_NomeD = (EditText)findViewById(R.id.etNome_tnc);
        et_endereco = (EditText)findViewById(R.id.etaddress_tnc);
        et_telefone = (EditText)findViewById(R.id.ettel_tnc);
        et_nomepet = (EditText)findViewById(R.id.etNomePet_tnc);
        et_codcoleira = (EditText)findViewById(R.id.etcod_tnc);
        buttonsalvar = (Button)findViewById(R.id.botaosalvar_tnc);
        buttonvoltar = (Button)findViewById(R.id.botaovoltar_tnc);

    }

    public void back_telacadastro (View view){
        Intent Telanovocadastro = new Intent(this, telacadastro.class);
        startActivity(Telanovocadastro);
    }

    //Método salvar

    public void salvar_tnc (View view){
        NomeDono = et_NomeD.getText().toString();
        Endereco = et_endereco.getText().toString();
        Telefone = et_telefone.getText().toString();
        NomePet = et_nomepet.getText().toString();
        IdColeira = et_codcoleira.getText().toString();

        DadosColeira valores = new DadosColeira(IdColeira,NomeDono, Endereco,Telefone, NomePet);

        // Adicionar valores ao banco de dados local
        idcadastro.setIDColeira(valores.getIdColeira());
        db.addDataBDLocal(idcadastro);

        //Adicionar valores ao banco de dados do Firebase

        DatabaseReference childref = rootRef.child(valores.getIdColeira());
        childref.setValue(valores);

    }
}
