package com.guy.class22b_and_9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private DayView dayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayView = findViewById(R.id.dayView);

        dayView.setOnHourClickListener(new DayView.OnHourClickListener() {
            @Override
            public void onClick(int hour) {
                cellClicked(hour);
            }
        });

        dayView.changeHourColor(18, Color.BLUE);

    }

    private void cellClicked(int h) {
        Toast.makeText(this, h + " Clicked", Toast.LENGTH_SHORT).show();
    }
}