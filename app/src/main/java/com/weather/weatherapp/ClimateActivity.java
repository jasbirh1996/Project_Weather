package com.weather.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ClimateActivity extends AppCompatActivity {

   Toolbar toolbar;
   TextView climate;
   TextView temperature;
   TextView city;
   Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Climate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClimateActivity.this, MainActivity.class));
            }
        });
        climate = findViewById(R.id.climate);
        temperature = findViewById(R.id.temperature);
        city = findViewById(R.id.city);
        intent = getIntent();
        //String result = intent.getStringExtra("result");
        String city_data = "Weather for " + intent.getStringExtra("city").toUpperCase();
        String data = intent.getStringExtra("myWeather").toUpperCase();
        String result = intent.getStringExtra("result") + " C";
        city.setText(city_data);
        climate.setText(data);
        temperature.setText(result);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.about:
                startActivity(new Intent(ClimateActivity.this,AboutActivity.class));
                finish();
                return true;

        }
        return false;
    }






}
