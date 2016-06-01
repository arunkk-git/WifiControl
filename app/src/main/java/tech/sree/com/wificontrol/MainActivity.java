package tech.sree.com.wificontrol;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] Title ={"Wifi Controller",""};
    String[] Descpription ={"From:     To:    ",""};
    boolean wifiControl = true;
    int[] imagesId = {R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock, R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock, R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock, R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock};

    boolean weekDays[] = {true, false, false, true, false, false, true};
    ///////////////////////
    private static MainActivity instance;
    public static MainActivity getInstance() {return instance;}
    private static void setInstance(MainActivity instance) {MainActivity.instance = instance;}
//////////////////////////

    final int ALARM_INFO = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInstance(this);
        listView = (ListView) findViewById(R.id.listView);

        //ListViewArrayAdaptor adaptor = new ListViewArrayAdaptor(this,R.layout.one_row,R.id.Title,Title,Descpription,imagesId);

        ListViewBaseAdaptor baseAdaptor = new ListViewBaseAdaptor(this, imagesId, Title, Descpription, true, weekDays);
        listView.setAdapter(baseAdaptor);
        ListViewUtils.onItemClick(this, listView);
    }
    public void SetAlaram(View V){
        Intent intent = new Intent(this,Wifi_SetAlarm.class);
        startActivityForResult(intent, ALARM_INFO);
        L.t(this, "SetAlaram");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ALARM_INFO && data != null){
            ArrayList<Integer> alarmInfo = data.getIntegerArrayListExtra("ALARM_INFO");
            L.t(this, alarmInfo.toString());
            ListViewUtils.UpdateListView(listView, alarmInfo);
            startAlarm(alarmInfo);
        }
    }
    AlarmService alarmService;
    private void startAlarm(ArrayList<Integer> alarmInfo){
        if(getWifiControl()) {
//            AlarmService
            alarmService = new AlarmService(this, alarmInfo, "WIFI");
        }
        else  L.t(this,"First Enable ");

    }
    protected void setWifiControl( boolean control){
        this.wifiControl = control;
        if(control == false)
            alarmService.cancleAlarmEvent();
    }
    public boolean getWifiControl( ){  return this.wifiControl;}
}


