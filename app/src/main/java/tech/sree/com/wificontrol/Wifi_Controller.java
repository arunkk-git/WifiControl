package tech.sree.com.wificontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Wifi_Controller extends AppCompatActivity {

    WifiManager wifiManager;
    final String WIFI_OFF = "Enable_Wifi_Control";
    final String WIFI_ON = "Disable_Wifi_Control";
    private static boolean initialStatus = false;
      static boolean present_ON_Status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wifi_controller);

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Bundle bundle = getIntent().getExtras();

        String resp1 = null;
        ImageView icon = (ImageView) findViewById(R.id.imageView);
        if (bundle != null) {
            resp1 = bundle.getString("WIFI");
            if (resp1.equals(WIFI_OFF)) {
                initialStatus = wifiManager.isWifiEnabled();
                L.l(" WIFI COntrol ON  initialStatus = " + initialStatus);
                present_ON_Status = false;
//                registerReceiver(wifi_broadcastReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
                toggleWiFi(false);

            } else if (resp1.equals(WIFI_ON)) {// && initialStatus) {
                L.l(" WIFI COntrol OFF  initialStatus = " + initialStatus);
                if (initialStatus) {
            /*check the initialStatus, if it's true then only Wifi will be ON after alarm.  else we simpley  ignore*/
                    L.l(" WIFI Control OFF ");
                    present_ON_Status = true;
                    toggleWiFi(true);
                }
//                unregisterReceiver(wifi_broadcastReceiver);
            }
            //   finish();
        }
    }
    public void toggleWiFi(boolean status) {
        if (status != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(status);
        }
    }
}
