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
        mmain mmain = new mmain();

//        mmain.username = "user1";
//        mmain.password = "pass1";
//        mmain.save();
//
//        mmain.username = "maskal";
//        mmain.password = "saintek";
//        mmain.save();

//        ArrayList<mmain> all = new mmain().all();
//        StringBuilder builder = new StringBuilder();
//        for(int i=0; i<all.size(); i++){
//            builder.append("\n");
//            builder.append("username = " + all.get(i).username + " password ="+ all.get(i).password);
//        }

        mmain first = new mmain().first();
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("username = " + first.username + " password ="+ first.password);

        TextView ok = (TextView) findViewById(R.id.ok);
        ok.setText(builder.toString());
    }
}