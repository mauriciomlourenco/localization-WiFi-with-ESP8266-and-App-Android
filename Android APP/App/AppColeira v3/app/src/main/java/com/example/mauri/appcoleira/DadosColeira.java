package com.example.mauri.appcoleira;


/**
 * Created by mauri on 20/09/2016.
 */
public class DadosColeira {

    private String IdColeira;
    private String NomeDonoPet;
    private String Endereco;
    private String Telefone;

    // Dados pet
    private String NomePet;
    private boolean status; //false= não perdido, true perdido

    //Dados Busca
    private String latitude;
    private String longitude;
    private String distancia;
    private String buscadatacompleta;            //data e hora em que a busca foi feita

    public DadosColeira (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public DadosColeira(String idcol,String nd,String end,String tel,String np){ // String data, String hora, GPSTracker GPS ){
        this.IdColeira = idcol;
        this.NomeDonoPet = nd;
        this.Endereco = end;
        this.Telefone = tel;
        this.NomePet = np;
        this.status = false;
        this.latitude = null;
        this.longitude = null;
        this.distancia = "0";
        this.buscadatacompleta = null;
    }

    public String getIdColeira() {
        return IdColeira;
    }

    public String getNomeDonoPet() {
        return NomeDonoPet;
    }

    public String getEndereco() {
        return Endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public String getNomePet() {
        return NomePet;
    }

    public boolean isStatus() {
        return status;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDistancia() {
        return distancia;
    }

    public String getBuscadatacompleta() {
        return buscadatacompleta;
    }
}
