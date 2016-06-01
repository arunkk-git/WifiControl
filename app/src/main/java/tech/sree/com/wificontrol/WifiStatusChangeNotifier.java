package tech.sree.com.wificontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

/**
 * Created by ananth on 6/1/2016.
 */
public class WifiStatusChangeNotifier extends BroadcastReceiver {
    WifiManager wifiManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Wifi_Controller.present_ON_Status
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        boolean isWifi = wifiManager.isWifiEnabled();

        L.l("WifiStatusChangeNotifier : "+ Wifi_Controller.present_ON_Status+" isWifiEnabled: "+isWifi);
        if (Wifi_Controller.present_ON_Status  == false && isWifi ==  true) {
            L.l("Forceing to Disable Wifi");
            wifiManager.setWifiEnabled(false);
        }
    }
}
