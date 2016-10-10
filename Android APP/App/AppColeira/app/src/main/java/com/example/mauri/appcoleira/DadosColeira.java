package com.example.mauri.appcoleira;


/**
 * Created by mauri on 20/09/2016.
 */
public class DadosColeira {
    String NomeDonoPet;
    String Endereco;
    String Telefone;
    // Dados pet
    String NomePet;
    String CodColeira;


    /*
    String data_status;
    String hora_status;
    //distância
    GPSTracker gps;
    */

    public DadosColeira(String nd,String end, String tel, String np, String cod){ // String data, String hora, GPSTracker GPS ){
        this.NomeDonoPet = nd;
        this.Endereco = end;
        this.Telefone = tel;
        this.NomePet = np;
        this.CodColeira = cod;
    }

    public DadosColeira NovoCadastro(DadosColeira dadostela){
        DadosColeira novocadastro = dadostela;

        return novocadastro;

    }

    public DadosColeira EditarCadastro(DadosColeira cadastro_edit){
        DadosColeira edit_cadastro = cadastro_edit;

        return edit_cadastro;
    }

  /*  public DadosColeira BuscaInfo(){
        DadosColeira info;

        return info;
    }
    */

    // Função para caso o usuário possua mais de uma coleira
    public void CadastrarNovaColeira (){

    }

}
