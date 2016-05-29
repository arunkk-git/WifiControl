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
    ListView listView ;
    String[] Title={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THRUSDAY","FRIDAY","SATURDAY","SUNDAY",
            "SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THRUSDAY","FRIDAY","SATURDAY","SUNDAY",
            "SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THRUSDAY","FRIDAY","SATURDAY","SUNDAY"};
    String[] Descpription ={"sun-day","mon-day","tues-day","wednes-day","thurs-day","fri-day","satur-day","sun-day",
            "sun-day","mon-day","tues-day","wednes-day","thurs-day","fri-day","satur-day","sun-day",
            "sun-day","mon-day","tues-day","wednes-day","thurs-day","fri-day","satur-day","sun-day"};
    int[] imagesId={R.drawable.alaramoff,R.drawable.alaramon,R.drawable.wifi0,R.drawable.wifi1,
            R.drawable.wifilock,R.drawable.wifiunlock,R.drawable.alaramoff,R.drawable.alaramon,R.drawable.wifi0,R.drawable.wifi1,
            R.drawable.wifilock,R.drawable.wifiunlock,R.drawable.alaramoff,R.drawable.alaramon,R.drawable.wifi0,R.drawable.wifi1,
            R.drawable.wifilock,R.drawable.wifiunlock,R.drawable.alaramoff,R.drawable.alaramon,R.drawable.wifi0,R.drawable.wifi1,
            R.drawable.wifilock,R.drawable.wifiunlock};

    boolean weekDays[]={true,false,false,true,false,false,true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //ListViewArrayAdaptor adaptor = new ListViewArrayAdaptor(this,R.layout.one_row,R.id.Title,Title,Descpription,imagesId);

        ListViewBaseAdaptor baseAdaptor = new ListViewBaseAdaptor(this,imagesId,Title,Descpription,true,weekDays);
        listView.setAdapter(baseAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView title = (TextView) view.findViewById(R.id.Title);
                TextView Description = (TextView) view.findViewById(R.id.description);
                Switch onoff = (Switch) view.findViewById(R.id.oNoFF);

                LinearLayout ll = (LinearLayout) view.findViewById(R.id.linearID);
                if (onoff.isChecked())
                    ll.setVisibility(View.GONE);
                else
                    ll.setVisibility(View.VISIBLE);

                ArrayList<Boolean> weekDay = new ArrayList<Boolean>();
                weekDay.add  (0,((ToggleButton) ll.findViewById(R.id.sun)).isChecked());
                weekDay.add  (1,((ToggleButton) ll.findViewById(R.id.mon)).isChecked());
                weekDay.add  (2,((ToggleButton) ll.findViewById(R.id.tue)).isChecked());
                weekDay.add  (3,((ToggleButton) ll.findViewById(R.id.wed)).isChecked());
                weekDay.add  (4,((ToggleButton) ll.findViewById(R.id.thu)).isChecked());
                weekDay.add  (5,((ToggleButton) ll.findViewById(R.id.fri)).isChecked());
                weekDay.add  (6,((ToggleButton) ll.findViewById(R.id.sat)).isChecked());
                String string =  "Title : "+title.getText().toString()+
                        "Description : +"+Description.getText().toString()
                        +"Switch : "+onoff.isChecked()+"\n WeekDays : "+weekDay.toString();

                Toast.makeText(getApplicationContext(), string,Toast.LENGTH_LONG).show();
            }
        });
    }
}


