package com.example.mauri.appcoleira;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class telaeditarcadastro extends AppCompatActivity {
    EditText et_Nome;
    EditText et_endereco;
    EditText et_telefone;
    EditText et_nomepet;
    EditText et_codcoleira;
    TextView testeBD;
    Button buttonsalvar;
    Button buttonvoltar;

    /* DADOS*/
    String NomeDono;
    String Endereco;
    String Telefone;
    String NomePet;
    String IdColeira;

    private IDColeiraCadastrado idcadastro = new IDColeiraCadastrado();
    public DadosColeira dadoscadastradoservidor = new DadosColeira();
    private DataBaseHandler db = new DataBaseHandler(this);

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    private DatabaseReference enderecobd = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaeditarcadastro);

        et_Nome = (EditText) findViewById(R.id.etNome_tec);
        et_endereco = (EditText) findViewById(R.id.etaddress_tec);
        et_telefone = (EditText) findViewById(R.id.ettel_tec);
        et_nomepet = (EditText) findViewById(R.id.etNomePet_tec);
        et_codcoleira = (EditText) findViewById(R.id.etcod_tec);
        buttonsalvar = (Button) findViewById(R.id.botaosalvar_tec);
        buttonvoltar = (Button) findViewById(R.id.botaovoltar_tec);

        //Buscar valores id coleira no banco de dados local
        idcadastro = db.getIDColeiraBD(1);

        testeBD = (TextView) findViewById(R.id.tv_testeBD);
        testeBD.setText(idcadastro.getIDColeira());

        //enderecobd = database.getReference("/" + idcadastro.getIDColeira());

        enderecobd.child(idcadastro.getIDColeira()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.i("Reading: ", "entrou child, lendo dados");
                        Log.i("dataSnapshot:", dataSnapshot.toString());
                        dadoscadastradoservidor = dataSnapshot.getValue(DadosColeira.class);
                        Log.i("dados lidos:", "Id coleira:" + dadoscadastradoservidor.getIdColeira());


                        et_Nome.setText(dadoscadastradoservidor.getNomeDonoPet());
                        et_endereco.setText(dadoscadastradoservidor.getEndereco());
                        et_telefone.setText(dadoscadastradoservidor.getTelefone());
                        et_nomepet.setText(dadoscadastradoservidor.getNomePet());
                        et_codcoleira.setText(dadoscadastradoservidor.getIdColeira());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("log", "The read failed: " + databaseError.getCode());
                    }
                }
        );

    }


    public void back_tcad(View view) {
        Intent Telacoleira = new Intent(this, telacoleira.class);
        startActivity(Telacoleira);
    }


    //método salvar
    public void salvar_tec(View view) {
        NomeDono = et_Nome.getText().toString();
        Endereco = et_endereco.getText().toString();
        Telefone = et_telefone.getText().toString();
        NomePet = et_nomepet.getText().toString();
        IdColeira = et_codcoleira.getText().toString();
        DatabaseReference enderecobd = rootRef;

        //Salvar Informações no servidor
        DadosColeira valores = new DadosColeira(IdColeira, NomeDono, Endereco, Telefone, NomePet);


        valores.setBuscadatacompleta(dadoscadastradoservidor.getBuscadatacompleta());
        valores.setDistancia(dadoscadastradoservidor.getDistancia());
        valores.setLatitude(dadoscadastradoservidor.getLatitude());
        valores.setLongitude(dadoscadastradoservidor.getLongitude());

        // Adicionar valores ao banco de dados local
        db.updateData(new IDColeiraCadastrado(valores.getIdColeira()));

        //Adicionar valores ao banco de dados do Firebase
        DatabaseReference childref = rootRef.child(valores.getIdColeira());
        childref.setValue(valores);

    }
}
