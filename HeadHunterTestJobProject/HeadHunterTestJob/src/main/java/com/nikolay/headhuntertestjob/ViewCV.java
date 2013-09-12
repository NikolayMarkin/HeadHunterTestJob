package com.nikolay.headhuntertestjob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewCV extends BaseActivity {

    // ключи для взаимодействия между активностями
    public final static String FULL_NAME = "com.nikolay.headhuntertestjob.FULLNAME";
    public final static String DATE_OF_BIRTH = "com.nikolay.headhuntertestjob.DATEOFBIRTH";
    public final static String GENDER = "com.nikolay.headhuntertestjob.GENDER";
    public final static String POSITION = "com.nikolay.headhuntertestjob.POSITION";
    public final static String SALARY = "com.nikolay.headhuntertestjob.SALARY";
    public final static String PHONE = "com.nikolay.headhuntertestjob.PHONE";
    public final static String EMAIL = "com.nikolay.headhuntertestjob.EMAIL";


    private TextView fullnameTextView;
    private TextView dateOfBirthTextView;
    private TextView genderTextView;
    private TextView positionTextView;
    private TextView salaryTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private EditText answerEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cv);

        fullnameTextView = (TextView) findViewById(R.id.fullnameTextView);
        dateOfBirthTextView = (TextView) findViewById(R.id.dateOfBirthTextView);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        positionTextView = (TextView) findViewById(R.id.positionTextView);
        salaryTextView = (TextView) findViewById(R.id.salaryTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        answerEditText = (EditText) findViewById(R.id.answerEditText);

        // получить дополнения
        Bundle extras = getIntent().getExtras();

        if (extras != null ){
            fullnameTextView.setText(extras.getString(FULL_NAME));
            dateOfBirthTextView.setText(extras.getString(DATE_OF_BIRTH));
            genderTextView.setText(extras.getString(GENDER));
            positionTextView.setText(extras.getString(POSITION));
            salaryTextView.setText(extras.getString(SALARY));
            phoneTextView.setText(extras.getString(PHONE));
            emailTextView.setText(extras.getString(EMAIL));
        }

        // установить обработчик нажатия кнопки "Отправить ответ"
        Button sendAnswerButton = (Button) findViewById(R.id.sendAnswerButton);
        sendAnswerButton.setOnClickListener(sendAnswerButtonClickListener);
    }

    OnClickListener sendAnswerButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // получить ответ и отправить его соискателю
            String answer = answerEditText.getText().toString();

            Intent answerMessage = new Intent();
            answerMessage.putExtra(MESSAGE, answer);
            answerMessage.setAction(SEND_MESSAGE);
            sendBroadcast(answerMessage);

            // возврат к предыдущему Activity
            finish();
        }
    };


}
