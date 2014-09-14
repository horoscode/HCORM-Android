package com.horoscode.hcorm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.horoscode.hcorm.model.*;


public class cmain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmain);
        mmain mmain = new mmain();
        HCORM.initWithDatabaseName("supersurvey");
        Toast.makeText(this, mmain.all(), Toast.LENGTH_SHORT).show();
    }
}