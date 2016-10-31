package com.example.mauri.appcoleira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BuscarAnimais extends AppCompatActivity {

    private Button botaoVoltar;
    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    //private StringBuilder sb = new StringBuilder();
    private TextView textView;
    private final Handler handler = new Handler();
    //private ListView lv;

    public static final String NomeRede = "TESTE_WIFI";

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_animais);

        //lv = (ListView) findViewById(R.id.listView);
        botaoVoltar=(Button)findViewById(R.id.botaovoltar_telabusca);
        textView =(TextView)findViewById(R.id.tv_telabusca);

        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        receiverWifi = new WifiReceiver();

        if(!mainWifi.isWifiEnabled()){
            mainWifi.setWifiEnabled(true);
        }

        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        doInback();
    }

    private void doInback() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

                if (receiverWifi==null) {
                    receiverWifi = new WifiReceiver();
                }

                registerReceiver(receiverWifi, new IntentFilter(
                        WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                mainWifi.startScan();

                doInback();
            }
        }, 10000); // 10 segundos
    }

    protected void OnPause(){
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume()
    {
        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    private class WifiReceiver extends BroadcastReceiver {

        public void onReceive (Context c, Intent intent){
            ArrayList<String> connections = new ArrayList<String>();
            //ArrayList<Float> Signal_Strenth = new ArrayList<Float>();
            //sb = new StringBuilder();
            List<ScanResult> wifiList;
            wifiList = mainWifi.getScanResults();
            String wifis[];
            wifis = new String[wifiList.size()];
            String teste;


            for(int i = 0; i < wifiList.size(); i++){
                connections.add(wifiList.get(i).SSID);

                wifis[i] = ((wifiList.get(i)).toString());
                //Signal_Strenth.add(wifiList.get(i).);

                teste = wifiList.get(i).SSID;

                if(teste.equals(NomeRede)){ // codificar nome da rede do transmissor para identificar um padrão
                    //COLETAR INFORMAÇÕES
                    coletaInfo(wifiList.get(i).BSSID, wifiList.get(i).level); //variável recebe retorno função, após armazena em buffer
                }
            }
            //lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,wifis));
        }
    }

    public void coletaInfo (String  id, int level){
        String texto;
        double latitude = 0, longitude = 0;
        double potrdb = level; //potência recebida em dbm
        double potrW; //Potência recebida em Wats
        double pottW; //Potência transmitida em Wats
        double distancia; // variável que armazena a distância (raio em m) do celular em relação ao módulo transmissor

        //buscar informações de data/hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        // instancia o service, GPSTracker gps
        GPSTracker gps = new GPSTracker(BuscarAnimais.this);

        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        }

        //Calculo da distancia utilizando a fórmula de Friiss
        // (Pr/Pt) = Gt*Gr* [(lambda/4*PI*D)]
        //lambda = C/F; C veloc. da luz, F frequência
        //lambda = 0.12248 // comprimento de onda do sinal Wi-Fi (Frequência = 2.4 GHz)

        //Calculo da distancia utilizando a fórmula de Friiss
        // (Pr/Pt) = Gt*Gr* [(lambda/4*PI*D)]
        //lambda = C/F; C veloc. da luz, F frequência
        //lambda = 0.12248 // comprimento de onda do sinal Wi-Fi (Frequência = 2.4 GHz)

        /* CONFIGURAR VALORES */
        int Gt = 1; //Ganho da antena de transmissão
        int Gr = 1; //Ganho da antena de recepção

        pottW = 0.1;
        potrW = (Math.pow(10, (potrdb/10))) * 0.001;

        distancia = (0.12248/(4 * Math.PI)) * Math.sqrt((pottW * Gt * Gr)/ (potrW));

        //Dados coletados: id, latitude, longitude, distância (Armazenar em uma classe?)
        texto ="Id:" + id.toString() + " Latitude:" + String.valueOf(latitude) + " Longitude:" + String.valueOf(longitude) + " Ditancia(m):" + String.valueOf(distancia)
        + " Data:" + data_completa;

        textView.setText(texto);

        //Necessário gravar daddos como transação

        //Alteração no banco de dados
        DatabaseReference childref = rootRef.child(id);

        DatabaseReference Lat = childref.child("latitude");
        Lat.setValue(String.valueOf(latitude));

        DatabaseReference Long = childref.child("longitude");
        Long.setValue(String.valueOf(longitude));

        DatabaseReference dist = childref.child("distancia");
        dist.setValue(String.valueOf(distancia));

        DatabaseReference date = childref.child("buscadatacompleta");
        date.setValue(data_completa);


    }

    public void back_tomain (View view){
        Intent mainactivity = new Intent(this, MainActivity.class);
        startActivity(mainactivity);
    }
}
