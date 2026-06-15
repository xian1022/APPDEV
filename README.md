# 漁港天氣提醒 App

Fishing Harbor Weather Alert App 是「114-2 APP 開發」期末專題，主題方向為「海事與海洋」。本專案是一個可直接用 Android Studio 開啟、編譯、執行的 Android App，功能包含漁港天氣查詢、風速與浪高安全判斷、最愛漁港、提醒設定、Google Maps/GPS，以及可部署到 AWS EC2 的 Flask 後端範例。

## 專案連結與路徑

### GitHub Repository

```text
https://github.com/xian1022/APPDEV
```

### 專案根目錄

```text
APPDEV
```

### Android Studio 開啟路徑

請在 Android Studio 選擇 Open，開啟你 clone 下來的專案根目錄，例如：

```text
APPDEV
```

請開啟 `APPDEV` 專案根目錄，不要只開 `app/` 資料夾，因為 Gradle Wrapper、根目錄 `build.gradle`、`settings.gradle` 都在專案根目錄。

## 專案資料夾結構

本專案依照課程 template 格式整理：

```text
APPDEV
├── README.md
├── settings.gradle
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── proposal/
│   └── proposal.md
├── my-topics/
│   └── topic_A11218001.md
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/example/fishingharboralert/
│       │   └── res/
│       └── test/
├── backend/
│   ├── app.py
│   └── requirements.txt
└── docs/
    ├── report.md
    ├── slides.pdf
    └── slides_outline.md
```

## 重要檔案路徑總覽

### Android 專案設定

| 用途 | 路徑 |
|---|---|
| 根目錄 Gradle 設定 | `build.gradle` |
| Gradle 專案模組設定 | `settings.gradle` |
| Android App Gradle 設定 | `app\build.gradle` |
| Gradle Wrapper | `gradlew.bat` |
| Android Manifest | `app\src\main\AndroidManifest.xml` |
| 本機 API key 設定 | `local.properties` |

`local.properties` 不會上傳 GitHub，因為裡面可能放 Android SDK 路徑與 API key。每位組員 clone 後都要在自己的電腦建立或確認這個檔案。

### Java 程式碼

| 類別 | 路徑 | 功能 |
|---|---|---|
| `MainActivity` | `app/src/main/java/com/example/fishingharboralert/MainActivity.java` | 首頁 Dashboard 與漁港列表 |
| `HarborDetailActivity` | `app/src/main/java/com/example/fishingharboralert/HarborDetailActivity.java` | 漁港詳細天氣、最愛、地圖入口 |
| `MapActivity` | `app/src/main/java/com/example/fishingharboralert/MapActivity.java` | Google Maps 與 GPS 定位 |
| `SettingsActivity` | `app/src/main/java/com/example/fishingharboralert/SettingsActivity.java` | 風速、浪高與提醒偏好設定 |
| `HarborAdapter` | `app/src/main/java/com/example/fishingharboralert/adapter/HarborAdapter.java` | RecyclerView 卡片顯示 |
| `WeatherRepository` | `app/src/main/java/com/example/fishingharboralert/api/WeatherRepository.java` | 天氣資料來源與 mock data fallback |
| `RetrofitClient` | `app/src/main/java/com/example/fishingharboralert/api/RetrofitClient.java` | Retrofit 初始化 |
| `WeatherApiService` | `app/src/main/java/com/example/fishingharboralert/api/WeatherApiService.java` | API interface |
| `AppDatabase` | `app/src/main/java/com/example/fishingharboralert/data/AppDatabase.java` | Room Database |
| `HarborDao` | `app/src/main/java/com/example/fishingharboralert/data/HarborDao.java` | Room DAO |
| `HarborEntity` | `app/src/main/java/com/example/fishingharboralert/data/HarborEntity.java` | 漁港資料表 |
| `FavoriteHarborEntity` | `app/src/main/java/com/example/fishingharboralert/data/FavoriteHarborEntity.java` | 最愛漁港資料表 |
| `AlertRuleEntity` | `app/src/main/java/com/example/fishingharboralert/data/AlertRuleEntity.java` | 提醒規則資料表 |
| `Harbor` | `app/src/main/java/com/example/fishingharboralert/model/Harbor.java` | 漁港 model |
| `WeatherInfo` | `app/src/main/java/com/example/fishingharboralert/model/WeatherInfo.java` | 天氣 model |
| `SafetyLevel` | `app/src/main/java/com/example/fishingharboralert/model/SafetyLevel.java` | 安全等級 enum |
| `SafetyEvaluator` | `app/src/main/java/com/example/fishingharboralert/util/SafetyEvaluator.java` | 風速浪高安全判斷 |
| `PreferenceManager` | `app/src/main/java/com/example/fishingharboralert/util/PreferenceManager.java` | SharedPreferences 管理 |

### XML Layout 與資源

| 畫面/資源 | 路徑 |
|---|---|
| 主頁 layout | `app/src/main/res/layout/activity_main.xml` |
| 漁港卡片 layout | `app/src/main/res/layout/item_harbor.xml` |
| 詳情頁 layout | `app/src/main/res/layout/activity_harbor_detail.xml` |
| 地圖頁 layout | `app/src/main/res/layout/activity_map.xml` |
| 設定頁 layout | `app/src/main/res/layout/activity_settings.xml` |
| 顏色設定 | `app/src/main/res/values/colors.xml` |
| 文字資源 | `app/src/main/res/values/strings.xml` |
| 樣式設定 | `app/src/main/res/values/styles.xml` |
| 安全標籤背景 | `app/src/main/res/drawable/bg_level_safe.xml`、`bg_level_caution.xml`、`bg_level_danger.xml` |
| App icon 前景 | `app/src/main/res/drawable/ic_launcher_foreground.xml` |

### 後端與文件

| 用途 | 路徑 |
|---|---|
| Flask 後端 | `backend/app.py` |
| Python 套件清單 | `backend/requirements.txt` |
| 企劃書 | `proposal/proposal.md` |
| 個人主題探索 | `my-topics/topic_A11218001.md` |
| 期末書面報告 | `docs/report.md` |
| 簡報 PDF | `docs/slides.pdf` |
| 簡報文字大綱 | `docs/slides_outline.md` |

## 功能介紹

### 1. 首頁 Dashboard

路徑：

```text
app/src/main/java/com/example/fishingharboralert/MainActivity.java
app/src/main/res/layout/activity_main.xml
app/src/main/res/layout/item_harbor.xml
```

功能：

- 使用 RecyclerView 顯示 8 個台灣代表漁港。
- 每張卡片顯示漁港名稱、城市、簡介、天氣摘要與安全等級。
- 點選漁港卡片後進入詳情頁。
- 可從首頁進入提醒設定頁。

### 2. 漁港詳情頁

路徑：

```text
app/src/main/java/com/example/fishingharboralert/HarborDetailActivity.java
app/src/main/res/layout/activity_harbor_detail.xml
```

功能：

- 顯示漁港名稱、城市、介紹。
- 顯示天氣、溫度、降雨機率、風速、浪高、潮汐與更新時間。
- 顯示 SAFE、CAUTION、DANGER 安全等級。
- 可加入或移除最愛漁港。
- 可跳轉 Google Maps 地圖頁。
- 風浪達 DANGER 時顯示 AlertDialog 警示。

### 3. Google Maps / GPS

路徑：

```text
app/src/main/java/com/example/fishingharboralert/MapActivity.java
app/src/main/res/layout/activity_map.xml
```

功能：

- 使用 `SupportMapFragment` 顯示 Google Map。
- 顯示漁港位置 marker。
- 使用者允許定位後，顯示目前 GPS 位置。
- 使用者拒絕定位時，仍可查看漁港 marker。

需要的 Android 權限在：

```text
app/src/main/AndroidManifest.xml
```

包含：

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

### 4. Room Database

路徑：

```text
app/src/main/java/com/example/fishingharboralert/data/
```

資料表：

- `HarborEntity`：漁港基本資料。
- `FavoriteHarborEntity`：使用者最愛漁港。
- `AlertRuleEntity`：提醒條件設定。

DAO：

```text
app/src/main/java/com/example/fishingharboralert/data/HarborDao.java
```

Database：

```text
app/src/main/java/com/example/fishingharboralert/data/AppDatabase.java
```

### 5. SharedPreferences

路徑：

```text
app/src/main/java/com/example/fishingharboralert/util/PreferenceManager.java
```

儲存內容：

- 上次查看的漁港名稱。
- 是否啟動 App 時自動開啟上次查看漁港。
- 是否啟用模擬推播提醒。

### 6. Retrofit API 與 mock data fallback

路徑：

```text
app/src/main/java/com/example/fishingharboralert/api/WeatherApiService.java
app/src/main/java/com/example/fishingharboralert/api/RetrofitClient.java
app/src/main/java/com/example/fishingharboralert/api/WeatherRepository.java
```

目前設計：

- 已建立 Retrofit API 架構，方便未來接中央氣象署或其他海象 API。
- 如果 `WEATHER_API_KEY` 為空，App 會自動使用 `WeatherRepository` 內建 mock data。
- 因此沒有 API key 也可以正常展示 App。

### 7. 安全等級判斷

路徑：

```text
app/src/main/java/com/example/fishingharboralert/util/SafetyEvaluator.java
app/src/main/java/com/example/fishingharboralert/model/SafetyLevel.java
```

規則：

| 條件 | 等級 | 說明 |
|---|---|---|
| 風速小於 8 m/s 且浪高小於 1.0 m | SAFE | 適合一般岸邊活動 |
| 風速 8 到 12 m/s 或浪高 1.0 到 2.0 m | CAUTION | 需注意天候與海象 |
| 風速大於 12 m/s 或浪高大於 2.0 m | DANGER | 避免靠近海邊或出航 |

測試檔：

```text
app/src/test/java/com/example/fishingharboralert/util/SafetyEvaluatorTest.java
```

## 組員第一次 clone 後怎麼執行

### 1. Clone 專案

```powershell
cd <你想放專案的資料夾>
git clone https://github.com/xian1022/APPDEV.git
cd APPDEV
```

如果已經用 Android Studio 從 GitHub Clone，直接開啟 clone 後的 `APPDEV` 根目錄即可。

### 2. 確認 JDK

建議使用 Android Studio 內建 JBR。若命令列建置失敗，可在 PowerShell 先設定：

```powershell
$env:JAVA_HOME='<Android Studio 安裝路徑>\jbr'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

### 3. 建立或確認 local.properties

本機檔案路徑：

```text
local.properties
```

每位組員的 `sdk.dir` 可能不同。請依自己的 Android SDK 安裝位置調整：

```properties
sdk.dir=<你的 Android SDK 路徑>
MAPS_API_KEY=
WEATHER_API_KEY=
```

若不知道 SDK 路徑，可以在 Android Studio 查看：

```text
File > Settings > Languages & Frameworks > Android SDK
```

### 4. Google Maps API key

若只要測試 App 清單、詳情頁、設定頁，可以先不填 `MAPS_API_KEY`。

若要讓 Google Maps 顯示底圖，請在 `local.properties` 填入：

```properties
MAPS_API_KEY=你的 Google Maps API Key
```

注意：不要把 API key 寫進 `AndroidManifest.xml` 或 Java 程式碼。API key 只放在 `local.properties`。

### 5. Android Studio 執行

1. 開啟 Android Studio。
2. 選擇 Open。
3. 選擇專案根目錄：`APPDEV` 或自己 clone 的 `APPDEV` 資料夾。
4. 等待 Gradle Sync。
5. 選擇 app configuration。
6. 按 Run。

## 命令列建置與測試

請在專案根目錄執行：

```powershell
cd <你的 clone 路徑>/APPDEV
$env:JAVA_HOME='<Android Studio 安裝路徑>\jbr'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

單元測試：

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

Debug APK 編譯：

```powershell
.\gradlew.bat assembleDebug
```

完整重新驗證：

```powershell
.\gradlew.bat :app:testDebugUnitTest assembleDebug --rerun-tasks
```

APK 產出路徑：

```text
app\build\outputs\apk\debug\app-debug.apk
```

`app/build/` 不會上傳 GitHub，因為這是編譯產物。

## Flask Backend 執行方式

後端路徑：

```text
backend
```

進入後端資料夾：

```powershell
cd backend
```

建立虛擬環境：

```powershell
python -m venv .venv
```

啟動虛擬環境：

```powershell
.\.venv\Scripts\Activate.ps1
```

安裝套件：

```powershell
pip install -r requirements.txt
```

啟動 API：

```powershell
python app.py
```

啟動後可測試：

```text
GET http://localhost:5000/health
GET http://localhost:5000/harbors
POST http://localhost:5000/alert-log
GET http://localhost:5000/alert-log
```

API 檔案：

```text
backend/app.py
```

套件清單：

```text
backend/requirements.txt
```

## AWS EC2 部署提示

報告或展示時可說明以下部署流程：

1. 建立 Ubuntu EC2 instance。
2. Security Group 開啟 22 port 與 5000 port。
3. SSH 進入 EC2。
4. 安裝 Python、pip、venv。
5. 上傳或 clone 本 repo。
6. 進入 `backend/`。
7. 安裝 `requirements.txt`。
8. 使用 `python app.py` 或 gunicorn 啟動。
9. 將 Android App API base url 改成 EC2 public IP 或 domain。

目前 Android App 的 API base url 在：

```text
app/build.gradle
```

欄位：

```gradle
buildConfigField 'String', 'API_BASE_URL', '"https://example.com/"'
```

## 報告撰寫位置與建議

書面報告路徑：

```text
docs\report.md
```

建議報告內容：

1. 摘要：說明 App 做什麼。
2. 開發動機：為什麼漁港風浪提醒重要。
3. 系統功能：主頁、詳情頁、地圖頁、設定頁、最愛功能。
4. 系統架構：Activity、Repository、Room、Retrofit、Flask。
5. Android 技術：Java、XML、RecyclerView、Material Components。
6. API 串接：Retrofit 架構與 mock data fallback。
7. 資料庫設計：三張 Room table。
8. Google Maps/GPS：marker、定位權限、拒絕權限處理。
9. AWS 後端：Flask API endpoint 與 EC2 部署流程。
10. 測試與驗證：`SafetyEvaluatorTest`、`assembleDebug`。
11. 困難與解法：API key、mock data、權限處理。
12. 未來改進：真實氣象署 API、通知推播、離線快取。

可以直接以目前的 `docs/report.md` 為初稿，再依老師要求補上截圖、組員分工與實測畫面。

## PPT 製作位置與建議

簡報 PDF 路徑：

```text
docs\slides.pdf
```

簡報文字大綱路徑：

```text
docs\slides_outline.md
```

建議 PPT 頁面：

1. 封面：專題名稱、課程、組員。
2. 專題背景：海邊活動與漁港安全需求。
3. 使用者痛點：資訊分散、風浪判斷不直覺。
4. 系統功能總覽：Dashboard、詳情、地圖、設定、最愛。
5. App 操作展示：首頁到詳情頁。
6. 技術架構：Java/XML、Room、Retrofit、Google Maps、Flask。
7. 資料與 API：mock data fallback、未來可接中央氣象署。
8. Google Maps/GPS：marker 與定位權限。
9. 後端與 AWS：Flask endpoint、EC2 部署。
10. 結論與未來改進。

建議 Demo 時錄製或截圖以下畫面放到 PPT：

- `activity_main.xml` 對應的首頁列表。
- `activity_harbor_detail.xml` 對應的詳細資料頁。
- `activity_settings.xml` 對應的提醒設定頁。
- `activity_map.xml` 對應的 Google Maps 頁。

## Demo 操作順序

建議期末展示照這個順序：

1. 開啟 App，展示首頁 8 個漁港清單。
2. 說明每張卡片顯示風速、浪高與安全等級。
3. 點選任一漁港進入詳情頁。
4. 說明天氣、溫度、降雨、風速、浪高、潮汐。
5. 點選加入最愛。
6. 點選查看 Google Map 位置。
7. 回到詳情頁，再進入提醒設定。
8. 修改風速與浪高警戒值。
9. 說明沒有 API key 時仍使用 mock data，不影響展示。
10. 簡短展示或說明 Flask backend API。

## Git 協作方式

### 下載最新版本

```powershell
git pull origin main
```

### 查看目前修改

```powershell
git status
```

### 提交修改

```powershell
git add .
git commit -m "docs: update report content"
git push origin main
```

### 不要提交的檔案

以下檔案或資料夾已在 `.gitignore`，不要手動加入 Git：

```text
local.properties
.idea/
.gradle/
build/
app/build/
backend/__pycache__/
backend/.venv/
```

## 常見問題

### 1. Android Studio 找不到 SDK

請確認 `local.properties`：

```properties
sdk.dir=<你的 Android SDK 路徑>
```

### 2. 命令列顯示 Java 版本太舊

請使用 Android Studio 內建 JBR：

```powershell
$env:JAVA_HOME='<Android Studio 安裝路徑>\jbr'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

### 3. Google Maps 沒有顯示底圖

請確認：

- `local.properties` 有填 `MAPS_API_KEY`。
- Google Cloud Console 已啟用 Maps SDK for Android。
- API key package name 與 SHA-1 設定正確。

### 4. 沒有 Weather API key 可以展示嗎

可以。`WEATHER_API_KEY` 留空時，App 會使用 mock weather data。

### 5. 後端一定要部署 AWS 嗎

不一定。課堂展示可以先用本機 Flask API 說明架構。AWS EC2 可作為加分或報告延伸內容。

## 驗證紀錄

目前專案已使用以下指令驗證過：

```powershell
.\gradlew.bat :app:testDebugUnitTest assembleDebug --rerun-tasks
python -m py_compile backend\app.py
```

驗證重點：

- Android 單元測試可通過。
- Debug APK 可成功編譯。
- Flask backend Python 語法正確。

## 專題完成狀態

| 項目 | 狀態 |
|---|---|
| Android Studio 專案格式 | 已完成 |
| Java + XML Layout | 已完成 |
| 多頁 Activity | 已完成 |
| RecyclerView | 已完成 |
| Room Database | 已完成 |
| SharedPreferences | 已完成 |
| Retrofit API 架構 | 已完成 |
| Mock weather data fallback | 已完成 |
| Google Maps / GPS | 已完成 |
| Flask backend | 已完成 |
| proposal.md | 已完成 |
| report.md | 已完成 |
| slides.pdf / slides_outline.md | 已完成 |
