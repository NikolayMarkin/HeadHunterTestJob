package com.nikolay.headhuntertestjob;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditCV extends BaseActivity {

    private int mYear;
    private int mMonth;
    private int mDay;
    private String mFullName;
    private String mGender;
    private String mPosition;
    private double mSalary;
    private String mPhone;
    private String mEmail;

    private TextView dateOfBirthSelectTextView;
    private EditText fullNameEditText;
    private Spinner genderSpinner;
    private EditText positionEditText;
    private EditText salaryEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    private MessageReceiver mMessageReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_cv);

        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SEND_MESSAGE);
        registerReceiver(mMessageReceiver, filter);

        fullNameEditText = (EditText) findViewById(R.id.fullnameEditText);
        genderSpinner = (Spinner) findViewById(R.id.genderSpiner);
        positionEditText = (EditText) findViewById(R.id.positionEditText);
        salaryEditText = (EditText) findViewById(R.id.salaryEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);

        dateOfBirthSelectTextView = (TextView) findViewById(R.id.dateOfBirthSelectTextView);
        dateOfBirthSelectTextView.setOnClickListener(dateOfBirthTextListener);

        Button sendCVButton = (Button) findViewById(R.id.sendCVButton);
        sendCVButton.setOnClickListener(sendCVButtonClicked);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMessageReceiver);
    }

    OnClickListener dateOfBirthTextListener = new OnClickListener() {
        @Override
        public void onClick(View view) {

            Calendar calendar = Calendar.getInstance();

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(EditCV.this,
                            onDateSetListener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );

            datePickerDialog.show();
        }
    };

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            dateOfBirthSelectTextView.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
        }
    };

    OnClickListener sendCVButtonClicked = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // преключиться к просмотру резюме
            Intent viewCV = new Intent(EditCV.this, ViewCV.class);
            // предать данные в виде дополнения к Intent
            viewCV.putExtra(FULL_NAME, fullNameEditText.getText().toString());
            viewCV.putExtra(DATE_OF_BIRTH, dateOfBirthSelectTextView.getText().toString());
            viewCV.putExtra(GENDER, genderSpinner.getSelectedItem().toString());
            viewCV.putExtra(POSITION, positionEditText.getText().toString());
            viewCV.putExtra(SALARY, salaryEditText.getText().toString());
            viewCV.putExtra(PHONE, phoneEditText.getText().toString());
            viewCV.putExtra(EMAIL, emailEditText.getText().toString());

            startActivity(viewCV);
        }
    };


    private class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // отобразить полученное сообщение соискателю
            String message = intent.getStringExtra(MESSAGE);
            new AlertDialog.Builder(context)
                    .setTitle(R.string.title_aswer)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, null)
                    .create().show();
        }
    }


}
