package com.example.fishingharboralert.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.fishingharboralert.BuildConfig;
import com.example.fishingharboralert.data.AppDatabase;
import com.example.fishingharboralert.data.HarborEntity;
import com.example.fishingharboralert.model.Harbor;
import com.example.fishingharboralert.model.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    public interface WeatherCallback {
        void onWeatherLoaded(WeatherInfo weatherInfo);
    }

    public interface HarborCallback {
        void onHarborsLoaded(List<Harbor> harbors);
    }

    private final AppDatabase database;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public WeatherRepository(Context context) {
        database = AppDatabase.getInstance(context);
    }

    public void loadHarbors(HarborCallback callback) {
        executor.execute(() -> {
            List<HarborEntity> entities = database.harborDao().getHarbors();
            if (entities.isEmpty()) {
                entities = sampleHarborEntities();
                database.harborDao().insertHarbors(entities);
            }
            List<Harbor> harbors = new ArrayList<>();
            for (HarborEntity entity : entities) {
                harbors.add(new Harbor(entity.id, entity.name, entity.city, entity.latitude, entity.longitude, entity.description));
            }
            mainHandler.post(() -> callback.onHarborsLoaded(harbors));
        });
    }

    public void loadWeather(Harbor harbor, WeatherCallback callback) {
        if (BuildConfig.WEATHER_API_KEY == null || BuildConfig.WEATHER_API_KEY.trim().isEmpty()) {
            mainHandler.post(() -> callback.onWeatherLoaded(mockWeather(harbor)));
            return;
        }

        RetrofitClient.getWeatherApiService()
                .getWeather(harbor.getName(), BuildConfig.WEATHER_API_KEY)
                .enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        WeatherInfo body = response.body();
                        callback.onWeatherLoaded(body != null ? body : mockWeather(harbor));
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable throwable) {
                        callback.onWeatherLoaded(mockWeather(harbor));
                    }
                });
    }

    public WeatherInfo mockWeather(Harbor harbor) {
        int seed = harbor.getId();
        double wind = 4.5 + (seed * 1.3 % 10.0);
        double wave = 0.4 + (seed * 0.35 % 2.3);
        String weather = seed % 3 == 0 ? "短暫陣雨" : seed % 2 == 0 ? "多雲時晴" : "晴朗有風";
        String tide = seed % 2 == 0 ? "漲潮" : "退潮";
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.TAIWAN).format(new Date());
        return new WeatherInfo(harbor.getName(), weather, 24 + seed % 7, 20 + seed * 7 % 70, wind, wave, tide, time);
    }

    public static List<HarborEntity> sampleHarborEntities() {
        List<HarborEntity> harbors = new ArrayList<>();
        harbors.add(new HarborEntity(1, "基隆港", "基隆市", 25.1524, 121.7392, "北台灣重要港口，鄰近和平島與八斗子海岸。"));
        harbors.add(new HarborEntity(2, "淡水漁人碼頭", "新北市", 25.1833, 121.4104, "適合觀光與夕陽景觀的熱門碼頭。"));
        harbors.add(new HarborEntity(3, "竹圍漁港", "桃園市", 25.1167, 121.2435, "桃園沿海漁貨與海岸休憩據點。"));
        harbors.add(new HarborEntity(4, "梧棲漁港", "臺中市", 24.2878, 120.5154, "中部大型漁港，鄰近高美濕地。"));
        harbors.add(new HarborEntity(5, "安平漁港", "臺南市", 22.9974, 120.1587, "結合歷史、海鮮與親水活動的港區。"));
        harbors.add(new HarborEntity(6, "旗津漁港", "高雄市", 22.6099, 120.2688, "高雄海岸觀光與漁業活動代表地點。"));
        harbors.add(new HarborEntity(7, "東港漁港", "屏東縣", 22.4672, 120.4387, "黑鮪魚季與小琉球航線重要港口。"));
        harbors.add(new HarborEntity(8, "蘇澳港", "宜蘭縣", 24.5959, 121.8708, "東北角重要港口，天候變化較明顯。"));
        return harbors;
    }
}
