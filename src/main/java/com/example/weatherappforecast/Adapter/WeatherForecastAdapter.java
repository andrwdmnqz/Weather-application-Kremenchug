package com.example.weatherappforecast.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherappforecast.Common.Common;
import com.example.weatherappforecast.Model.WeatherForecastResult;
import com.example.weatherappforecast.R;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    Context context;
    WeatherForecastResult weatherForecastResult;

    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.one_item_forecast, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(new StringBuilder("http://openweathermap.org/img/w/")
                .append(weatherForecastResult.list.get(position).weather.get(0).getIcon())
                .append(".png").toString()).into(holder.img_view);

        holder.txt_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherForecastResult.list
                .get(position).dt)));

        holder.txt_description.setText(new StringBuilder(weatherForecastResult.list.get(position)
                .weather.get(0).getDescription()));

        holder.txt_temperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(position)
                .main.getTemp())).append("°C"));
    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date_time, txt_description, txt_temperature;
        ImageView img_view;

        public MyViewHolder(View view) {
            super(view);

            img_view = (ImageView) itemView.findViewById(R.id.img_weather);
            txt_date_time = (TextView) itemView.findViewById(R.id.day_of_week);
            txt_description = (TextView) itemView.findViewById(R.id.weather_status);
            txt_temperature = (TextView) itemView.findViewById(R.id.temperature_field);
        }
    }
}
