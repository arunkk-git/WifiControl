package tech.sree.com.wificontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PassWord extends AppCompatActivity {
    EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        setTitle("Pass-Word");
        pwd = (EditText) findViewById(R.id.password);
    }
    public void verifyPassWord(View V){
        L.t(this,"verifyPassWord : "+pwd.getText().toString());
        finish();

    }
}
