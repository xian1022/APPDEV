# 漁港天氣提醒 App 專題企劃書

## 1. 專題名稱

漁港天氣提醒 App（Fishing Harbor Weather Alert App）

## 2. 專題背景

漁港是觀光、交通與漁業活動的重要場域，但海邊天候變化快速，風速與浪高可能影響安全。本 App 以大學期末專題規模設計，將天氣資料、漁港位置與提醒設定整合到一個可展示的 Android 專案。

## 3. 使用者需求

使用者需要快速查看不同漁港目前的風浪狀況、辨識安全等級、儲存常用漁港，並在需要時透過地圖確認位置。

## 4. 系統目標

建立一個可在 Android Studio 開啟、編譯與執行的 Java/XML App，具備 Room 本機資料庫、Retrofit API 架構、Google Maps、SharedPreferences 與 Flask backend 範例。

## 5. 主要功能

- Dashboard 顯示代表漁港與安全狀態。
- HarborDetailActivity 顯示詳細天氣與操作按鈕。
- MapActivity 顯示漁港 marker 與 GPS 位置。
- SettingsActivity 設定風速、浪高與提醒選項。
- API key 不存在時使用 mock weather data。

## 6. 技術架構

Android 端採 Java、XML Layout、RecyclerView、Material Components、Room、Retrofit、Google Maps SDK。後端採 Flask 與 Flask-CORS，提供 `/health`、`/harbors`、`/alert-log` API。

## 7. 資料來源

課堂展示預設使用 sample data。若未來取得天氣 API key，可在 `local.properties` 設定並擴充 `WeatherApiService`。

## 8. 預期成果

完成可展示的 Android App、可部署的 Flask API 範例、README、報告與簡報大綱，讓期末展示能說明完整開發流程。
