package com.nikolay.headhuntertestjob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BaseActivity extends Activity {

    // ключи для взаимодействия между активностями
    public final static String FULL_NAME = "com.nikolay.headhuntertestjob.FULLNAME";
    public final static String DATE_OF_BIRTH = "com.nikolay.headhuntertestjob.DATEOFBIRTH";
    public final static String GENDER = "com.nikolay.headhuntertestjob.GENDER";
    public final static String POSITION = "com.nikolay.headhuntertestjob.POSITION";
    public final static String SALARY = "com.nikolay.headhuntertestjob.SALARY";
    public final static String PHONE = "com.nikolay.headhuntertestjob.PHONE";
    public final static String EMAIL = "com.nikolay.headhuntertestjob.EMAIL";
    public final static String CLOSE = "com.nikolay.headhuntertestjob.CLOSE";


    private KillReceiver mKillReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter(CLOSE);
        registerReceiver(mKillReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mKillReceiver);
    }

    @Override
    public void onBackPressed() {
        // спросить пользователя, действительно ли он хочет выйти
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_exit)
                .setMessage(R.string.message_exit)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // при согласии выйти из приложения

                        finish();

                        final Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                        intent.setAction(CLOSE);
                        sendBroadcast(intent);
                    }
                }).create().show();
    }

    public class KillReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
}
