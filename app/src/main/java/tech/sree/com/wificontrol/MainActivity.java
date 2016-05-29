package tech.sree.com.wificontrol;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ToggleButton;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] Title = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THRUSDAY", "FRIDAY", "SATURDAY", "SUNDAY",
            "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THRUSDAY", "FRIDAY", "SATURDAY", "SUNDAY",
            "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THRUSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    String[] Descpription = {"sun-day", "mon-day", "tues-day", "wednes-day", "thurs-day", "fri-day", "satur-day", "sun-day",
            "sun-day", "mon-day", "tues-day", "wednes-day", "thurs-day", "fri-day", "satur-day", "sun-day",
            "sun-day", "mon-day", "tues-day", "wednes-day", "thurs-day", "fri-day", "satur-day", "sun-day"};
    int[] imagesId = {R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock, R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock, R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock, R.drawable.alaramoff, R.drawable.alaramon, R.drawable.wifi0, R.drawable.wifi1,
            R.drawable.wifilock, R.drawable.wifiunlock};

    boolean weekDays[] = {true, false, false, true, false, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //ListViewArrayAdaptor adaptor = new ListViewArrayAdaptor(this,R.layout.one_row,R.id.Title,Title,Descpription,imagesId);

        ListViewBaseAdaptor baseAdaptor = new ListViewBaseAdaptor(this, imagesId, Title, Descpription, true, weekDays);
        listView.setAdapter(baseAdaptor);
        ListViewUtils.onItemClick(this, listView);
    }
}


