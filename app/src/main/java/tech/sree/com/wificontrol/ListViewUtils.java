package tech.sree.com.wificontrol;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by ananth on 5/30/2016.
 */
public class ListViewUtils {
    static Context ContextList ;
    ListView listView;
    ListViewUtils(Context context,ListView listView){
        this.ContextList = context;
        this.listView = listView;
    }
    static void onItemClick(final Context context,ListView listView){
        ContextList = context;
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

                Toast.makeText( context , string, Toast.LENGTH_LONG).show();

            }
        });
    }
    static void  UpdateListView(ListView listView,ArrayList<Integer> alarmInfo){

        String title = "From : "+convertToString(alarmInfo,0);
        String Desc = " To : "+convertToString(alarmInfo,2);

        View v = listView.getChildAt(0 -
                listView.getFirstVisiblePosition());


        TextView Title = (TextView) v.findViewById(R.id.Title);
        Title.setText(title);

        TextView desc = (TextView) v.findViewById(R.id.description);
        desc.setText(Desc);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linearID);

    }

    static String convertToString(ArrayList<Integer> alarmInfo,int index){
        StringBuilder timingInfo =  new StringBuilder();

        String temp = (alarmInfo.get(index) < 10) ? ("0"+alarmInfo.get(index).toString()) : (alarmInfo.get(index).toString());
        timingInfo.append(temp+" : ");

        temp = (alarmInfo.get(index+1) < 10) ? ("0"+alarmInfo.get(index+1).toString()) : (alarmInfo.get(index+1).toString());
        timingInfo.append(temp);

        return  timingInfo.toString();
    }
}

