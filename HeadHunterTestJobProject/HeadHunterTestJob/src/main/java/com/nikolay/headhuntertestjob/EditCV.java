package com.nikolay.headhuntertestjob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

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

    private EditText dateOfBirthEditText;
    private EditText fullNameEditText;
    private Spinner genderSpinner;
    private EditText positionEditText;
    private EditText salaryEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        setContentView(R.layout.new_cv);

        fullNameEditText = (EditText) findViewById(R.id.fullnameEditText);
        genderSpinner = (Spinner) findViewById(R.id.genderSpiner);
        positionEditText = (EditText) findViewById(R.id.positionEditText);
        salaryEditText = (EditText) findViewById(R.id.salaryEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);

        dateOfBirthEditText = (EditText) findViewById(R.id.dateOfBirthEditText);
        dateOfBirthEditText.setOnClickListener(dateOfBirthTextListener);

        Button sendCVButton = (Button) findViewById(R.id.sendCVButton);
        sendCVButton.setOnClickListener(sendCVButtonClicked);
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
            mYear = year;
            mMonth = month;
            mDay = day;

            Date chosedDate = new Date(year, month, day);

            dateOfBirthEditText.setText(new SimpleDateFormat("yyyy-MM-dd").format(chosedDate));
        }
    };

    OnClickListener sendCVButtonClicked = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // преключиться к просмотру резюме
            Intent viewCV = new Intent(EditCV.this, ViewCV.class);
            // предать данные в виде дополнения к Intent
            viewCV.putExtra(FULL_NAME, fullNameEditText.getText().toString());
            viewCV.putExtra(DATE_OF_BIRTH, dateOfBirthEditText.getText().toString());
            viewCV.putExtra(GENDER, genderSpinner.getSelectedItem().toString());
            viewCV.putExtra(POSITION, positionEditText.getText().toString());
            viewCV.putExtra(SALARY, salaryEditText.getText().toString());
            viewCV.putExtra(PHONE, phoneEditText.getText().toString());
            viewCV.putExtra(EMAIL, emailEditText.getText().toString());

            startActivity(viewCV);
        }
    };


}
