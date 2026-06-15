# 漁港天氣提醒 App 期末報告

## 1. 摘要

本專題製作一款 Android 漁港天氣提醒 App，讓使用者查詢台灣代表漁港的天氣、風速、浪高、潮汐與安全等級。專案可直接在 Android Studio 開啟、編譯與執行。

## 2. 開發動機

漁港周邊活動常受天候與海象影響。若能用手機快速看到風浪狀態與安全提醒，可提升行前判斷效率，也能作為海洋生活安全教育的應用範例。

## 3. 系統功能

- 主頁 Dashboard：以 RecyclerView 顯示漁港卡片。
- 詳情頁：顯示天氣、溫度、降雨、風速、浪高、潮汐與安全說明。
- 地圖頁：使用 Google Maps Fragment 顯示漁港 marker 與使用者定位。
- 設定頁：使用 EditText、Switch 與 Room 儲存提醒條件。
- 最愛功能：使用 Room 儲存與移除常用漁港。
- Mock data fallback：沒有 API key 時仍可展示完整流程。

## 4. 系統架構

App 採用 Activity 分頁設計：`MainActivity` 負責清單，`HarborDetailActivity` 負責詳細資訊，`MapActivity` 負責 Google Maps，`SettingsActivity` 負責提醒設定。資料層由 `WeatherRepository` 統一管理 API 與 mock data。

## 5. Android 技術

專案使用 Java、XML layout、Material Components、RecyclerView、Room、Retrofit、Google Maps SDK 與 SharedPreferences。Room 管理漁港、最愛漁港與提醒規則，SharedPreferences 儲存使用者偏好。

## 6. API 串接

`WeatherApiService` 與 `RetrofitClient` 保留真實 API 串接架構。若 `WEATHER_API_KEY` 為空，`WeatherRepository` 自動回傳 mock weather data，確保課堂展示穩定。

## 7. 資料庫設計

- `HarborEntity`：漁港基本資料。
- `FavoriteHarborEntity`：最愛漁港。
- `AlertRuleEntity`：風速、浪高與危險提醒設定。

## 8. Google Maps 與 GPS

`MapActivity` 使用 `SupportMapFragment` 顯示漁港位置，並透過 `FusedLocationProviderClient` 嘗試取得使用者 GPS。若未授權定位，App 仍顯示漁港 marker。

## 9. AWS EC2 後端

`backend/app.py` 提供 Flask API 範例，可部署到 EC2。API 包含健康檢查、漁港清單與提醒紀錄。

## 10. 測試與驗證

本專案包含 `SafetyEvaluatorTest`，確認風速與浪高可正確判斷 SAFE、CAUTION、DANGER。開發時已執行 `:app:testDebugUnitTest` 與 `assembleDebug`。

## 11. 問題與改進方向

目前天氣資料預設為 mock data。未來可串接中央氣象署或海象 API，並加入背景排程、通知推播、離線快取與更完整的錯誤重試策略。

## 12. 結論

本 App 完成 Android 期末專題所需的前端畫面、資料庫、API 架構、地圖定位、設定功能與文件，可作為海事與海洋主題的完整展示作品。
