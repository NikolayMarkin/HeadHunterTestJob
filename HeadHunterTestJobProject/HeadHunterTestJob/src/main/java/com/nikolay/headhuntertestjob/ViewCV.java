package com.nikolay.headhuntertestjob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ViewCV extends Activity {

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.view_cv);

//        Button sendCVButton = (Button) findViewById(R.id.sendCVButton);
//        sendCVButton.setOnClickListener(sendCVButtonClicked);
    }

//    View.OnClickListener sendCVButtonClicked = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            // преключиться к просмотру резюме
//            Intent viewCV = new Intent(EditCV.this, ViewCV.class);
//
//            startActivity(viewCV);
//
//        }
//    }
}
