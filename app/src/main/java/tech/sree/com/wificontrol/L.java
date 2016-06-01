package tech.sree.com.wificontrol;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ananth on 5/31/2016.
 */
public class L {
    public static void  t(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    public static void l(String msg){
        Log.d("ARUN", msg);
    }
}
