package com.example.fishingharboralert;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fishingharboralert.data.AlertRuleEntity;
import com.example.fishingharboralert.data.AppDatabase;
import com.example.fishingharboralert.util.PreferenceManager;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingsActivity extends AppCompatActivity {
    private EditText windEdit;
    private EditText waveEdit;
    private Switch dangerSwitch;
    private Switch openLastSwitch;
    private Switch pushSwitch;
    private AppDatabase database;
    private PreferenceManager preferenceManager;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        database = AppDatabase.getInstance(this);
        preferenceManager = new PreferenceManager(this);
        windEdit = findViewById(R.id.editWindLimit);
        waveEdit = findViewById(R.id.editWaveLimit);
        dangerSwitch = findViewById(R.id.switchDangerAlert);
        openLastSwitch = findViewById(R.id.switchOpenLast);
        pushSwitch = findViewById(R.id.switchPushAlert);

        openLastSwitch.setChecked(preferenceManager.shouldOpenLastHarbor());
        pushSwitch.setChecked(preferenceManager.isPushAlertEnabled());
        openLastSwitch.setOnCheckedChangeListener(this::onOpenLastChanged);
        pushSwitch.setOnCheckedChangeListener(this::onPushChanged);

        MaterialButton saveButton = findViewById(R.id.buttonSaveSettings);
        MaterialButton backButton = findViewById(R.id.buttonSettingsBack);
        saveButton.setOnClickListener(v -> saveSettings());
        backButton.setOnClickListener(v -> finish());
        loadRule();
    }

    private void onOpenLastChanged(CompoundButton buttonView, boolean isChecked) {
        preferenceManager.setOpenLastHarbor(isChecked);
    }

    private void onPushChanged(CompoundButton buttonView, boolean isChecked) {
        preferenceManager.setPushAlertEnabled(isChecked);
    }

    private void loadRule() {
        executor.execute(() -> {
            AlertRuleEntity rule = database.harborDao().getAlertRule();
            if (rule == null) {
                rule = new AlertRuleEntity(1, 12.0, 2.0, true);
                database.harborDao().saveAlertRule(rule);
            }
            AlertRuleEntity loaded = rule;
            runOnUiThread(() -> {
                windEdit.setText(String.valueOf(loaded.maxWindSpeed));
                waveEdit.setText(String.valueOf(loaded.maxWaveHeight));
                dangerSwitch.setChecked(loaded.enableDangerAlert);
            });
        });
    }

    private void saveSettings() {
        double wind;
        double wave;
        try {
            wind = Double.parseDouble(windEdit.getText().toString().trim());
            wave = Double.parseDouble(waveEdit.getText().toString().trim());
        } catch (NumberFormatException exception) {
            Toast.makeText(this, "請輸入有效的風速與浪高數字", Toast.LENGTH_SHORT).show();
            return;
        }
        executor.execute(() -> {
            database.harborDao().saveAlertRule(new AlertRuleEntity(1, wind, wave, dangerSwitch.isChecked()));
            runOnUiThread(() -> Toast.makeText(this, "提醒條件已儲存", Toast.LENGTH_SHORT).show());
        });
    }
}
