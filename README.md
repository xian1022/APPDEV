# 漁港天氣提醒 App

Fishing Harbor Weather Alert App 是 114-2 APP 開發期末專題。專案使用 Java 與 XML Layout 製作 Android App，整合漁港清單、風速浪高安全判斷、最愛漁港、提醒設定、Google Maps/GPS 與 Flask backend 範例。

## 專案結構

```text
app/                  Android App
backend/              Flask API 範例
proposal/             專題企劃書
my-topics/            主題說明
docs/report.md        期末報告
docs/slides_outline.md 簡報大綱
```

## 主要功能

- Dashboard 顯示 8 個台灣代表漁港。
- 詳情頁顯示天氣、溫度、降雨機率、風速、浪高、潮汐與安全等級。
- SAFE / CAUTION / DANGER 風浪安全判斷。
- Room Database 儲存漁港、最愛漁港與提醒規則。
- SharedPreferences 儲存上次查看漁港與提醒偏好。
- Google Maps 顯示漁港 marker，並支援 GPS 定位。
- Retrofit API 架構，沒有 API key 時自動使用 mock data。
- Flask backend 提供 `/health`、`/harbors`、`/alert-log`。

## Android Studio 執行方式

1. 用 Android Studio 開啟本資料夾：`C:\Users\39165\Desktop\APPDEV`
2. 等待 Gradle Sync 完成。
3. 確認 `local.properties` 內有 Android SDK 路徑：

```properties
sdk.dir=C\:\\Users\\39165\\AppData\\Local\\Android\\Sdk
MAPS_API_KEY=
WEATHER_API_KEY=
```

4. 若要使用 Google Maps，將 Google Maps API key 填入：

```properties
MAPS_API_KEY=你的 Google Maps API Key
```

5. 按 Android Studio 的 Run 執行 App。

## API Key 說明

`WEATHER_API_KEY` 留空時，App 會使用 mock weather data，不影響課堂展示。`MAPS_API_KEY` 留空時，地圖 SDK 可能無法載入底圖，但 App 不會因為缺少 key 而無法編譯。

## Room Database

- `HarborEntity`：漁港名稱、城市、經緯度、介紹。
- `FavoriteHarborEntity`：使用者加入的最愛漁港。
- `AlertRuleEntity`：風速、浪高與危險提醒設定。

## Google Maps / GPS

`MapActivity` 使用 `SupportMapFragment` 呈現地圖，並在取得定位權限後顯示使用者位置。若使用者拒絕定位，仍會顯示漁港 marker。

## Flask Backend

```powershell
cd backend
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
python app.py
```

測試 API：

```text
GET http://localhost:5000/health
GET http://localhost:5000/harbors
POST http://localhost:5000/alert-log
GET http://localhost:5000/alert-log
```

## AWS EC2 部署概要

1. 建立 Ubuntu EC2 instance，開啟 22 與 5000 port。
2. 安裝 Python、pip、venv。
3. 上傳 `backend/` 到 EC2。
4. 安裝 `requirements.txt`。
5. 使用 `python app.py` 或 gunicorn 啟動服務。
6. 將 Android App 的 API base url 改成 EC2 public IP 或 domain。

## 驗證指令

```powershell
$env:JAVA_HOME='C:\Program Files\Android\Android Studio\jbr'
.\gradlew.bat :app:testDebugUnitTest
.\gradlew.bat assembleDebug
```

## 期末展示建議

1. 展示主頁漁港清單與安全等級。
2. 點選危險或注意等級漁港，說明風速與浪高判斷。
3. 加入最愛漁港。
4. 開啟 Google Maps 頁面。
5. 進入設定頁修改警戒條件。
6. 說明 mock data fallback 與 Flask API 架構。
