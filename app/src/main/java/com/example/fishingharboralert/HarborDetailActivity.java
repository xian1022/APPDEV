package com.example.fishingharboralert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fishingharboralert.data.AppDatabase;
import com.example.fishingharboralert.data.FavoriteHarborEntity;
import com.example.fishingharboralert.model.Harbor;
import com.example.fishingharboralert.model.SafetyLevel;
import com.example.fishingharboralert.model.WeatherInfo;
import com.example.fishingharboralert.util.SafetyEvaluator;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HarborDetailActivity extends AppCompatActivity {
    private Harbor harbor;
    private WeatherInfo weatherInfo;
    private AppDatabase database;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harbor_detail);

        harbor = (Harbor) getIntent().getSerializableExtra("harbor");
        weatherInfo = (WeatherInfo) getIntent().getSerializableExtra("weatherInfo");
        database = AppDatabase.getInstance(this);

        if (harbor == null || weatherInfo == null) {
            Toast.makeText(this, "缺少漁港資料", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        bindWeather();
        findViewById(R.id.buttonBack).setOnClickListener(v -> finish());
        findViewById(R.id.buttonMap).setOnClickListener(v -> openMap());
        findViewById(R.id.buttonSettings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        findViewById(R.id.buttonFavorite).setOnClickListener(v -> toggleFavorite());
        checkDangerAlert();
    }

    private void bindWeather() {
        SafetyLevel level = SafetyEvaluator.evaluate(weatherInfo.getWindSpeed(), weatherInfo.getWaveHeight());
        ((TextView) findViewById(R.id.textDetailName)).setText(harbor.getName());
        ((TextView) findViewById(R.id.textDetailCity)).setText(harbor.getCity());
        ((TextView) findViewById(R.id.textDetailDescription)).setText(harbor.getDescription());
        ((TextView) findViewById(R.id.textDetailWeather)).setText(weatherInfo.getWeatherDescription());
        ((TextView) findViewById(R.id.textDetailTemperature)).setText(String.format("%.1f°C", weatherInfo.getTemperature()));
        ((TextView) findViewById(R.id.textDetailWind)).setText(String.format("%.1f m/s", weatherInfo.getWindSpeed()));
        ((TextView) findViewById(R.id.textDetailWave)).setText(String.format("%.1f m", weatherInfo.getWaveHeight()));
        ((TextView) findViewById(R.id.textDetailRain)).setText(weatherInfo.getRainProbability() + "%");
        ((TextView) findViewById(R.id.textDetailTide)).setText(weatherInfo.getTideStatus());
        ((TextView) findViewById(R.id.textDetailUpdate)).setText(weatherInfo.getUpdateTime());

        TextView levelText = findViewById(R.id.textDetailLevel);
        levelText.setText(level.getLabel() + "｜" + level.getDescription());
        if (level == SafetyLevel.DANGER) {
            levelText.setBackgroundResource(R.drawable.bg_level_danger);
        } else if (level == SafetyLevel.CAUTION) {
            levelText.setBackgroundResource(R.drawable.bg_level_caution);
        } else {
            levelText.setBackgroundResource(R.drawable.bg_level_safe);
        }
    }

    private void openMap() {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("harbor", harbor);
        startActivity(intent);
    }

    private void toggleFavorite() {
        executor.execute(() -> {
            int count = database.harborDao().countFavorite(harbor.getName());
            if (count > 0) {
                database.harborDao().removeFavorite(harbor.getName());
                runOnUiThread(() -> Toast.makeText(this, "已移除最愛漁港", Toast.LENGTH_SHORT).show());
            } else {
                database.harborDao().addFavorite(new FavoriteHarborEntity(
                        harbor.getName(), harbor.getCity(), harbor.getLatitude(), harbor.getLongitude(), System.currentTimeMillis()));
                runOnUiThread(() -> Toast.makeText(this, "已加入最愛漁港", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void checkDangerAlert() {
        SafetyLevel level = SafetyEvaluator.evaluate(weatherInfo.getWindSpeed(), weatherInfo.getWaveHeight());
        if (level == SafetyLevel.DANGER) {
            new AlertDialog.Builder(this)
                    .setTitle("危險提醒")
                    .setMessage("目前風浪已達危險等級，建議避免靠近海邊或安排出航。")
                    .setPositiveButton("知道了", null)
                    .show();
        }
    }
}
