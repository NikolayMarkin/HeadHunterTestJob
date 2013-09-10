package com.nikolay.headhuntertestjob;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditCV extends Activity {

    private int mYear;
    private int mMonth;
    private int mDay;

    private EditText dateOfBirthEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_cv);

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
            startActivity(viewCV);
        }
    };
}
