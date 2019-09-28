package com.weather.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.weather.weatherapp.R.menu.menu;

public class MainActivity extends AppCompatActivity {

    /***
     * created by Jasbir singh...
     */

    Button button;
    EditText city;
    String baseUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    String Api = "&units=metric&appid=use your own Api";
    TextView climate;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city= findViewById(R.id.city);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = city.getText().toString();


                if (TextUtils.isEmpty(txt)) {
                    Toast.makeText(MainActivity.this, "Please Enter the Field", Toast.LENGTH_SHORT).show();
                } else {
                    String myUrl = baseUrl + txt + Api;
                    intent = new Intent(MainActivity.this, ClimateActivity.class);
                    intent.putExtra("city",txt);
                    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                       String info = jsonObject.getString("weather");
                                        JSONArray ar = new JSONArray(info);
                                       for (int i = 0; i<ar.length(); i++) {

                                            JSONObject parObj = ar.getJSONObject(i);
                                            String myWeather = parObj.getString("description");
                                          // intent.putExtra("climate",myWeather);
                                           // climate.setText(myWeather);
                                           intent.putExtra("myWeather",myWeather);


                                        }

                                        String temp = jsonObject.getString("main");
                                        JSONObject obj = new JSONObject(temp);
                                        String result = obj.getString("temp");
                                         intent.putExtra("result",result);
                                        //startActivity(new Intent(MainActivity.this,ClimateActivity.class));
                                        startActivity(intent);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "You have Enter wrong keyword", Toast.LENGTH_SHORT).show();

                        }
                    }

                    );
                    VolleySingelton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }

            }
        });


    }


}
