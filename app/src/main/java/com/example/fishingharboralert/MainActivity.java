package com.example.fishingharboralert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingharboralert.adapter.HarborAdapter;
import com.example.fishingharboralert.api.WeatherRepository;
import com.example.fishingharboralert.model.Harbor;
import com.example.fishingharboralert.model.WeatherInfo;
import com.example.fishingharboralert.util.PreferenceManager;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HarborAdapter adapter;
    private WeatherRepository repository;
    private PreferenceManager preferenceManager;
    private ProgressBar progressBar;
    private TextView summaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new WeatherRepository(this);
        preferenceManager = new PreferenceManager(this);
        progressBar = findViewById(R.id.progressLoading);
        summaryText = findViewById(R.id.textSummary);

        RecyclerView recyclerView = findViewById(R.id.recyclerHarbors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HarborAdapter(this::openDetail);
        recyclerView.setAdapter(adapter);

        MaterialButton settingsButton = findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));

        loadHarbors();
    }

    private void loadHarbors() {
        progressBar.setVisibility(View.VISIBLE);
        repository.loadHarbors(this::onHarborsLoaded);
    }

    private void onHarborsLoaded(List<Harbor> harbors) {
        progressBar.setVisibility(View.GONE);
        adapter.submitHarbors(harbors);
        summaryText.setText("已載入 " + harbors.size() + " 個台灣代表漁港，點選卡片查看詳細天氣與地圖。");
        for (Harbor harbor : harbors) {
            repository.loadWeather(harbor, adapter::updateWeather);
        }
        maybeOpenLastHarbor(harbors);
    }

    private void maybeOpenLastHarbor(List<Harbor> harbors) {
        if (!preferenceManager.shouldOpenLastHarbor()) {
            return;
        }
        String lastName = preferenceManager.getLastHarborName();
        if (lastName.isEmpty()) {
            return;
        }
        for (Harbor harbor : harbors) {
            if (harbor.getName().equals(lastName)) {
                repository.loadWeather(harbor, weatherInfo -> openDetail(harbor, weatherInfo));
                return;
            }
        }
    }

    private void openDetail(Harbor harbor, WeatherInfo weatherInfo) {
        if (weatherInfo == null) {
            Toast.makeText(this, "天氣資料仍在載入，請稍後再試", Toast.LENGTH_SHORT).show();
            return;
        }
        preferenceManager.setLastHarborName(harbor.getName());
        Intent intent = new Intent(this, HarborDetailActivity.class);
        intent.putExtra("harbor", harbor);
        intent.putExtra("weatherInfo", weatherInfo);
        startActivity(intent);
    }
}
