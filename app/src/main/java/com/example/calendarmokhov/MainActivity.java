package com.example.calendarmokhov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndtDateCalendar;
    private Button mBtnOK;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndtDateCalendar = findViewById(R.id.endDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);
        mStartDateCalendar.setVisibility(View.GONE);
        mEndtDateCalendar.setVisibility(View.GONE);
        mChooseStartDate.setOnClickListener(view -> {
            mStartDateCalendar.setVisibility(View.VISIBLE);
            mEndtDateCalendar.setVisibility(View.GONE);
        });

        mChooseEndDate.setOnClickListener(view -> {
            mEndtDateCalendar.setVisibility(View.VISIBLE);
            mStartDateCalendar.setVisibility(View.GONE);
        });
        mStartDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {

        });

        mEndtDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {

        });
        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mStartDateTxt = i+"-"+i1+"-"+i2;
                mChooseStartDate.setText(getString(R.string.startDate) + mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mEndtDateCalendar.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            mEndDateTxt = i+"-"+i1+"-"+i2;
            mChooseEndDate.setText(R.string.endDate + mEndDateTxt);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(i, i1, i2);
            mEndDate = gregorianCalendar.getTimeInMillis();
            calendarView.setVisibility(View.GONE);
        });
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mStartDate >= mEndDate) || ((mStartDate == 0) || (mEndDate == 0))){
                    Toast.makeText(MainActivity.this, R.string.warn, Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText(R.string.startDate);
                    mChooseEndDate.setText(R.string.endDate);
                } else {
                    Toast.makeText(MainActivity.this, R.string.start + mStartDateTxt + R.string.done + mEndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}