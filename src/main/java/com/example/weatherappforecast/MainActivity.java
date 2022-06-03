package com.example.weatherappforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView currentWeather;
    private TextView currentWeatherFeels;

    String cityName = "Кременчук";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        currentWeather = findViewById(R.id.currentWeather);
        currentWeatherFeels = findViewById(R.id.currentWeatherFeels);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentURL = "https://api.openweathermap.org/data/2.5/weather?lat=49.0629553&lon=33.403516&appid=7da1236c7e4678c080ca4209b07c2f9b&units=metric&lang=ua";

                new GetWeatherInfo().execute(currentURL);
            }
        });
    }

    private class GetWeatherInfo extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection cnnctn = null;
            BufferedReader br = null;

            try {
                URL link = new URL(strings[0]);
                cnnctn = (HttpURLConnection) link.openConnection();
                cnnctn.connect();

                InputStream is = cnnctn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));

                StringBuffer sb = new StringBuffer();
                String str = "";

                while((str = br.readLine()) != null) {
                    sb.append(str).append("\n");
                }
                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(cnnctn != null) {
                    cnnctn.disconnect();
                }
                if(br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            return null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            currentWeather.setText("Завантаження...");
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);

            try {
                JSONObject jso = new JSONObject(res);
                //JSONArray jsa = new JSONArray(jso.getJSONArray("weather"));
                //JSONObject currentDescription = jsa[1];

                //currentWeather.setText("Погодний стан:");
                currentWeather.setText("Температура: " + Math.round(jso.getJSONObject("main").getDouble("temp")) + ", " + jso.getJSONArray("weather").getJSONObject(0).getString("description"));
                //currentWeatherFeels.setText("Відчувається як: " + Math.round(jso.getJSONObject("main").getDouble("feels_like")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}