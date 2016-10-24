package com.example.mauri.appcoleira;


/**
 * Created by mauri on 20/09/2016.
 */
public class DadosColeira{
    private int id;

    private String IdColeira;
    private String NomeDonoPet;
    private String Endereco;
    private String Telefone;

    // Dados pet
    private String NomePet;
    private String status; //false= não perdido, true perdido

    //Dados Busca
    private String latitude;
    private String longitude;
    private String distancia;
    private String buscadatacompleta;            //data e hora em que a busca foi feita

    public DadosColeira (){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public DadosColeira(int ID,String idcol,String nd,String end,String tel,String np, String stat, String lat, String Long, String dist, String data){ // String data, String hora, GPSTracker GPS ){
        boolean temp = false;
        this.id = ID;
        this.IdColeira = idcol;
        this.NomeDonoPet = nd;
        this.Endereco = end;
        this.Telefone = tel;
        this.NomePet = np;
        this.status = String.valueOf(temp);
        this.latitude = "0";
        this.longitude = "0";
        this.distancia = "0";
        this.buscadatacompleta = "0";
    }



    public DadosColeira(String idcol, String nd, String end, String tel, String np){ // String data, String hora, GPSTracker GPS ){
        boolean temp = false;
        this.IdColeira = idcol;
        this.NomeDonoPet = nd;
        this.Endereco = end;
        this.Telefone = tel;
        this.NomePet = np;
        this.status = String.valueOf(temp);
        this.latitude = "0";
        this.longitude = "0";
        this.distancia = "0";
        this.buscadatacompleta = "0";
    }

    public DadosColeira (DadosColeira d){
        this.IdColeira = d.getIdColeira();
        this.NomeDonoPet = d.getNomeDonoPet();
        this.Endereco = d.getEndereco();
        this.Telefone = d.getTelefone();
        this.NomePet = d.getNomePet();
        this.status = d.isStatus();
        this.latitude = d.getLatitude();
        this.longitude = d.getLongitude();
        this.distancia = d.getDistancia();
        this.buscadatacompleta = d.getBuscadatacompleta();
    }

    public DadosColeira(String idcol,String nd,String end,String tel,String np, String stat, String lat, String Long, String dist, String data){ // String data, String hora, GPSTracker GPS ){
        this.IdColeira = idcol;
        this.NomeDonoPet = nd;
        this.Endereco = end;
        this.Telefone = tel;
        this.NomePet = np;
        this.status = stat;
        this.latitude = lat;
        this.longitude = Long;
        this.distancia = dist;
        this.buscadatacompleta = data;
    }

    public DadosColeira setDadosColeira (DadosColeira dados){
        DadosColeira dadosalterados = new DadosColeira(dados);

        return dadosalterados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String isStatus() {
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

    public void setIdColeira(String idColeira) {
        IdColeira = idColeira;
    }

    public void setNomeDonoPet(String nomeDonoPet) {
        NomeDonoPet = nomeDonoPet;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public void setNomePet(String nomePet) {
        NomePet = nomePet;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setBuscadatacompleta(String buscadatacompleta) {
        this.buscadatacompleta = buscadatacompleta;
    }


}
