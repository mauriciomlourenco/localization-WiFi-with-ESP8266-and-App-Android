package com.example.mauri.appcoleira;
import android.Manifest;
import android.app.AlertDialog;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;



/**
 * Created by mauri on 19/09/2016.
 */

public class GPSTracker extends Service implements LocationListener {
    private final Context mContext;

    // flag para GPS status
    boolean isGPSEnabled = false;

    // flag para network status
    boolean isNetworkEnabled = false;

    // flag para GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    // A distância mínima para mudar Atualizações em metros
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 metros

    // O tempo mínimo entre as atualizações em milissegundos
    private static final long MIN_TIME_BW_UPDATES = 0; // 1 minute=1000 * 60 * 1

    // Declarar a Location Manager
    protected LocationManager locationManager;

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {

        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // obter o status GPS
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // obter o status da rede
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                //Nenhum provedor de rede está habilitado
            } else {
                this.canGetLocation = true;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    //DO CHECK PERMISSION

                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        //DO OP WITH LOCATION SERVICE

                        // Primeiro localização obter de provedor de rede
                        if (isNetworkEnabled) {

                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            Log.d("Network", "Network");
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                        // if GPS Enabled get lat/long using GPS Services
                        if (isGPSEnabled) {
                            if (location == null) {
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER,
                                        MIN_TIME_BW_UPDATES,
                                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                                Log.d("GPS Enabled", "GPS Enabled");
                                if (locationManager != null) {
                                    location = locationManager
                                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                    if (location != null) {
                                        latitude = location.getLatitude();
                                        longitude = location.getLongitude();
                                    }
                                }
                            }
                        }


                    }


                }else{
                    // Primeiro localização obter de provedor de rede
                    if (isNetworkEnabled) {

                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("Network", "Network");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                    // if GPS Enabled get lat/long using GPS Services
                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            Log.d("GPS Enabled", "GPS Enabled");
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                    }
                }




            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    //eric

    /**
     * para de usar GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                //DO CHECK PERMISSION

                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //DO OP WITH LOCATION SERVICE

                    locationManager.removeUpdates(GPSTracker.this);

                }
            } else {
                locationManager.removeUpdates(GPSTracker.this);
            }
        }
    }

    /**
     * Function para obter latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function para obetr longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Função para verificar GPS / Wi-Fi habilitado
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    /**
     * Function para abrir o alerta
     * */
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // titulo da mensagem de config
        alertDialog.setTitle("Configurar GPS");

        // mensagem de configuração
        alertDialog.setMessage("GPS não Ativado. Você deseja abrir o menu de configuração?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Configuração", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // btn cancelar
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Exibir alerta de mensagem
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }
}

/* Exemplo de uso da classe

// clicando no botao
btnShowLocation.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View arg0) {
        // instancia o service, GPSTracker gps
        gps = new GPSTracker(MainActivity.this);

        // verifica ele
        if (gps.canGetLocation()) {
            // passa sua latitude e longitude para duas variaveis
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // e mostra no Toast
            Toast.makeText(getApplicationContext(), "Sua localização - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
    }
});

 */
