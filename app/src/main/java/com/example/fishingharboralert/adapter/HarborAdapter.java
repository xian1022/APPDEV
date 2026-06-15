package com.example.fishingharboralert.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingharboralert.R;
import com.example.fishingharboralert.model.Harbor;
import com.example.fishingharboralert.model.SafetyLevel;
import com.example.fishingharboralert.model.WeatherInfo;
import com.example.fishingharboralert.util.SafetyEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HarborAdapter extends RecyclerView.Adapter<HarborAdapter.HarborViewHolder> {
    public interface OnHarborClickListener {
        void onHarborClick(Harbor harbor, WeatherInfo weatherInfo);
    }

    private final List<Harbor> harbors = new ArrayList<>();
    private final Map<String, WeatherInfo> weatherMap = new HashMap<>();
    private final OnHarborClickListener listener;

    public HarborAdapter(OnHarborClickListener listener) {
        this.listener = listener;
    }

    public void submitHarbors(List<Harbor> newHarbors) {
        harbors.clear();
        harbors.addAll(newHarbors);
        notifyDataSetChanged();
    }

    public void updateWeather(WeatherInfo info) {
        weatherMap.put(info.getHarborName(), info);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HarborViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_harbor, parent, false);
        return new HarborViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HarborViewHolder holder, int position) {
        Harbor harbor = harbors.get(position);
        WeatherInfo weather = weatherMap.get(harbor.getName());
        holder.nameText.setText(harbor.getName());
        holder.cityText.setText(harbor.getCity());
        holder.descriptionText.setText(harbor.getDescription());

        if (weather == null) {
            holder.weatherText.setText("載入天氣中...");
            holder.levelText.setText("待更新");
            holder.levelText.setBackgroundResource(R.drawable.bg_level_neutral);
        } else {
            holder.weatherText.setText(String.format("風速 %.1f m/s｜浪高 %.1f m｜%s",
                    weather.getWindSpeed(), weather.getWaveHeight(), weather.getWeatherDescription()));
            SafetyLevel level = SafetyEvaluator.evaluate(weather.getWindSpeed(), weather.getWaveHeight());
            holder.levelText.setText(level.getLabel());
            holder.levelText.setBackgroundResource(backgroundFor(level));
        }

        holder.itemView.setOnClickListener(v -> listener.onHarborClick(harbor, weather));
    }

    @Override
    public int getItemCount() {
        return harbors.size();
    }

    private int backgroundFor(SafetyLevel level) {
        if (level == SafetyLevel.DANGER) {
            return R.drawable.bg_level_danger;
        }
        if (level == SafetyLevel.CAUTION) {
            return R.drawable.bg_level_caution;
        }
        return R.drawable.bg_level_safe;
    }

    static class HarborViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView cityText;
        TextView descriptionText;
        TextView weatherText;
        TextView levelText;

        HarborViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textHarborName);
            cityText = itemView.findViewById(R.id.textHarborCity);
            descriptionText = itemView.findViewById(R.id.textHarborDescription);
            weatherText = itemView.findViewById(R.id.textHarborWeather);
            levelText = itemView.findViewById(R.id.textSafetyLevel);
        }
    }
}
