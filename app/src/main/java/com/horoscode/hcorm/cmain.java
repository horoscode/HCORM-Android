package com.horoscode.hcorm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.horoscode.hcorm.model.*;

import java.util.ArrayList;
import java.util.List;


public class cmain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmain);
        HCORM.initWithDatabaseName("supersurvey");
        User mmain = new User();

//        for(int i=0; i<100; i++) {
            mmain.username = "maskal";
            mmain.password = "saintek";
            mmain.save();
//        }
        mmain.user_id = 4;
        mmain.username = "jajal";
        mmain.save();

        List<User> all = new User().all();

        StringBuilder builder = new StringBuilder();
        for(int i=0; i<all.size(); i++){
            builder.append("user_id = "+all.get(i).user_id+" username = " + all.get(i).username + " password ="+ all.get(i).password);
            builder.append("\n");
        }
//        builder.append(mmain.username);

//        mmain first = new mmain().first();
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("username = " + first.username + " password ="+ first.password);

        TextView ok = (TextView) findViewById(R.id.ok);
        ok.setText(builder.toString());
    }
}