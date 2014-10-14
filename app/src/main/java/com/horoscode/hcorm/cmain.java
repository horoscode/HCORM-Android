package com.horoscode.hcorm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.horoscode.hcorm.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class cmain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmain);
        HCORM.initWithDatabaseName("supersurvey");
        User mmain = new User();

//        for(int i=0; i<100; i++) {
            mmain.username = "kenek";
            mmain.password = "pora";
            mmain.save();
//        }
//        mmain.user_id = 4;
//        mmain.username = "jajal";
//        mmain.save();
//        String a= "test";
//

//        User user = new User().last();


//        List<User> all = new User().all();

        StringBuilder builder = new StringBuilder();
//        builder.append(user)
//        for(int i=0; i<all.size(); i++){
//            builder.append("user_id = "+all.get(i).user_id+" username = " + all.get(i).username + " password ="+ all.get(i).password);
//            if(all.get(i).user_id == 6){
//                all.get(i).destroy();
//            }
//            builder.append("\n");
//        }

//        mmain first = new mmain().first();
//        StringBuilder builder = new StringBuilder();

        builder.append("id = " + mmain.user_id +" username = " + mmain.username + " password = "+ mmain.password);

        TextView ok = (TextView) findViewById(R.id.ok);
        ok.setText(builder.toString());
    }
}