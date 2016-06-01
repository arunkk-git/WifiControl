package tech.sree.com.wificontrol;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Wifi_SetAlarm extends AppCompatActivity {
    TimePicker timerON ,timerOFF ;
    EditText password;

    Button saveAlaram;
    private ArrayList<Integer> AlaramTimings = null;

    private static Wifi_SetAlarm instance;
    public static Wifi_SetAlarm getInstance() {return instance;}
    private static void setWifi_SetAlarmInstance(Wifi_SetAlarm instance) {Wifi_SetAlarm.instance = instance;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi__set_alarm);
        setWifi_SetAlarmInstance(this);
        setTitle("Set Time");
        timerON = (TimePicker) findViewById(R.id.wifiON);
        timerOFF = (TimePicker) findViewById(R.id.wifiOFF);
        password = (EditText) findViewById(R.id.password);
        saveAlaram = (Button) findViewById(R.id.saveAlaram);
        //startActivity(new Intent(this,PassWord.class));

    }
    private boolean verifyPassWord(String password){
        L.l( "Entered : " + password);
      //  L.l("getpresentPassword : "+MainActivity.getInstance().getpresentPassword());

        return true;
    }
    public ArrayList<Integer> getAlarmTimings(){
        return AlaramTimings;
    }
    public void getTimeData(View V){

        if(verifyPassWord(password.getText().toString())){
            saveAlaram.setBackgroundColor(Color.GREEN);
            setAlaramTimings();

            Intent intent=new Intent();
            intent.putIntegerArrayListExtra("ALARM_INFO", this.getAlarmTimings());
            setResult(321, intent);
            finish();
        }else {
            saveAlaram.setBackgroundColor(Color.RED);
            L.t(this, "Wrong ENter !!! Need to check password");
        }

    }

    private void setAlaramTimings(){
        //ArrayList<Integer>
        AlaramTimings =  new ArrayList<Integer>();
        Calendar c = Calendar.getInstance();

        int hour = 0;
        int minute = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timerOFF.getHour();
            minute = timerOFF.getMinute();
        }else {
            hour = timerOFF.getCurrentHour();
            minute = timerOFF.getCurrentMinute();
        }
        if (hour <= c.get(Calendar.HOUR ) && minute <= c.get(Calendar.MINUTE ) ){
            Toast.makeText(this,"Set Proper Future Time !!!",Toast.LENGTH_LONG).show();
        }

        AlaramTimings.add(hour);
        AlaramTimings.add(minute);
        hour = 0;
        minute = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timerON.getHour();
            minute = timerON.getMinute();
        }else {
            hour = timerON.getCurrentHour();
            minute = timerON.getCurrentMinute();
        }

        if (hour < c.get(Calendar.HOUR ) && minute <= c.get(Calendar.MINUTE ) ){
            Toast.makeText(this,"Set Proper Future Time !!!",Toast.LENGTH_LONG).show();
        }
        AlaramTimings.add(hour);
        AlaramTimings.add(minute);
    }
}

