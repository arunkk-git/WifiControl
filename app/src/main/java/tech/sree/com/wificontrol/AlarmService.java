package tech.sree.com.wificontrol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ananth on 5/4/2016.
 */
public class AlarmService {
    Context context;
    private  int OnTimeHour = 0 ,OnTimeMinuts = 0 , OffTimeHour = 0, OffTimeMinuts = 0 ;
    private PendingIntent piON = null,piOFF= null;
    private AlarmManager amOFF=null,amON = null;

    public AlarmService(Context context, ArrayList<Integer> timerInfo, String toContorl){
        this.context = context ;
        OnTimeHour = timerInfo.get(0) ;
        OnTimeMinuts = timerInfo.get(1) ;
        OffTimeHour = timerInfo.get(2);
        OffTimeMinuts = timerInfo.get(3) ;

        if(MainActivity.getInstance().getWifiControl())
            set_AlarmToContorl(toContorl);
    }

    public void set_AlarmToContorl(String toContorl) {
        int hour = 0;
        int minute = 0;

        Calendar futureDateON = Calendar.getInstance();
        Calendar futureDateOFF = Calendar.getInstance();


        futureDateON.set(Calendar.HOUR_OF_DAY, OnTimeHour);
        futureDateON.set(Calendar.MINUTE, OnTimeMinuts);
        futureDateON.set(Calendar.SECOND,10);

        futureDateOFF.set(Calendar.HOUR_OF_DAY, OffTimeHour);
        futureDateOFF.set(Calendar.MINUTE, OffTimeMinuts);
        futureDateOFF.set(Calendar.SECOND,5);

        if (toContorl == "WIFI") {
            Log.d("ARUN","WIFI toContorl");

            Intent intentON = new Intent(context, Wifi_Controller.class);
            intentON.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentON.putExtra("WIFI", "Enable_Wifi_Control");

//            PendingIntent
                    piON = PendingIntent.getActivity(this.context, 2, intentON, PendingIntent.FLAG_CANCEL_CURRENT);
//            AlarmManager
                    amON = (AlarmManager) this.context.getSystemService(context.ALARM_SERVICE);
            amON.set(AlarmManager.RTC_WAKEUP, futureDateON.getTimeInMillis(), piON);

            Intent intentOFF = new Intent(this.context, Wifi_Controller.class);
            intentOFF.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentOFF.putExtra("WIFI", "Disable_Wifi_Control");

            //PendingIntent
                    piOFF = PendingIntent.getActivity(this.context, 3, intentOFF, PendingIntent.FLAG_CANCEL_CURRENT);
            //AlarmManager
                    amOFF = (AlarmManager) this.context.getSystemService(context.ALARM_SERVICE);
            amOFF.set(AlarmManager.RTC_WAKEUP, futureDateOFF.getTimeInMillis(), piOFF);

        }
    }

    public void cancleAlarmEvent(){
        if(amOFF != null)
            amOFF.cancel(piOFF);
        if (amON != null)
            amON.cancel(piON);
    }
}
