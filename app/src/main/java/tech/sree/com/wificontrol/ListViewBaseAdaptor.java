package tech.sree.com.wificontrol;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by ananth on 5/28/2016.
 */
class  SignleRow{
    int imageView;
    String Title;
    String Description;
    boolean onOff;
    boolean[] week;
    public SignleRow(int imageView, String title, String description, boolean onOff,boolean [] week) {
        this.imageView = imageView;
        this.Title = title;
        this.Description = description;
        this.onOff = onOff;
        this.week=week;
    }
};
public class ListViewBaseAdaptor extends BaseAdapter {

    ArrayList<SignleRow> list;
    Context context;
    ListViewBaseAdaptor( Context context,int[] images,String[] title,String[] desc,boolean onOFF,boolean[] weekDays){
        this.context = context;

        list = new ArrayList<SignleRow>();
        int size = title.length-1;
     //   boolean[] week={true,false,true,false,false,true,false,true};
        for(int i = 0 ; i < size ;i++){
            list.add(new SignleRow(images[i],title[i],desc[i],onOFF,weekDays));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.one_row, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }else {
            holder = (MyViewHolder) row.getTag();
        }
        ImageView icon = holder.imageView;
        TextView decs = holder.Description;
        TextView title = holder.Title;
        Switch onOff = holder.onOff;
        ArrayList<ToggleButton> week_Day = holder.weekDay;

        title.setText(list.get(position).Title);
        decs.setText(list.get(position).Description);
        //icon.setImageResource(list.get(position).imageView);
        onOff.setChecked(list.get(position).onOff);

        for(int i = 0 ;i<7;i++){
            week_Day.get(i).setChecked(list.get(position).week[i]);
        }

        return row;
    }

    class MyViewHolder{
        ImageView imageView;
        TextView Title;
        TextView Description;
        Switch onOff;
        ArrayList<ToggleButton> weekDay = new ArrayList<ToggleButton>();

        MyViewHolder(View V){
            this.imageView  = (ImageView) V.findViewById(R.id.imageView);
            this.Description = (TextView) V.findViewById(R.id.description);
            this.Title = (TextView) V.findViewById(R.id.Title);
            this.onOff = (Switch) V.findViewById(R.id.oNoFF);

            LinearLayout ll = (LinearLayout) V.findViewById(R.id.linearID);

            weekDay.add(0,(ToggleButton) ll.findViewById(R.id.sun));
            weekDay.add(1,(ToggleButton) ll.findViewById(R.id.mon));
            weekDay.add(2, (ToggleButton) ll.findViewById(R.id.tue));
            weekDay.add(3, (ToggleButton) ll.findViewById(R.id.wed));
            weekDay.add(4, (ToggleButton) ll.findViewById(R.id.thu));
            weekDay.add(5, (ToggleButton) ll.findViewById(R.id.fri));
            weekDay.add(6, (ToggleButton) ll.findViewById(R.id.sat));

            Log.d("ARUN", "Dome");

        }
    }
}
