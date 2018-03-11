package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    TextView detailedWeatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO DONE(2) Display the weather forecast that was passed from MainActivity
        detailedWeatherData = (TextView) findViewById(R.id.detail_weather_text);
        if (getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            detailedWeatherData.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}