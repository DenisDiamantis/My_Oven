package com.example.myoven;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Temperature extends AppCompatActivity {
    public static final String MODE="default";
    private static SeekBar seek_bar;
    private  TextView hour;
    private  TextView minutes;
    private  TextView text_view;
    private String mode;
    private int sentHour;
    private int sentMinute;
    TextView display;
    private NumberPicker picker;
    private NumberPicker hours;
    private Button next;
    private Button back;
    private int temperature;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);
        next=findViewById(R.id.next);
        back=findViewById(R.id.back);
        mode=getIntent().getStringExtra(MODE);
        display=findViewById(R.id.mode);
        display.setText(mode);
        picker=findViewById(R.id.minutepicker);
        picker.setMaxValue(59);
        picker.setMinValue(0);
        hour=findViewById(R.id.hours);
        minutes=findViewById(R.id.minutes);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minutes.setText(newVal+" λεπτά");
                sentMinute=newVal;
            }
        });

        hours=findViewById(R.id.hourpicker);
        hours.setMaxValue(5);
        hours.setMinValue(0);
        hours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                hour.setText(newVal+" ώρες");
                sentHour=newVal;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        seekbarr();
    }
    public void seekbarr( ){
        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        text_view =(TextView)findViewById(R.id.temp);
        text_view.setText("Επιλέξτε Θερμοκρασία: " + seek_bar.getProgress());
        seek_bar.setMax(25);

        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText("Θερμοκρασία : " + 10*seek_bar.getProgress());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_view.setText("Θερμοκρασία : " + 10*seek_bar.getProgress());
                        temperature=10*seek_bar.getProgress();
                    }
                }
        );

    }
    public void next(){
        Intent intent = new Intent(this,Progress.class);
        intent.putExtra(Progress.MODE,mode);
        intent.putExtra(Progress.TEMPERATURE,String.valueOf(temperature));
        intent.putExtra(Progress.HOUR,String.valueOf(sentHour));
        intent.putExtra(Progress.MINUTE,String.valueOf(sentMinute));
        startActivity(intent);
    }
    public void back(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
